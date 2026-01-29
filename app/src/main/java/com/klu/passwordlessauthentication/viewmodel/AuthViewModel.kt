package com.klu.passwordlessauthentication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.klu.passwordlessauthentication.analytics.AnalyticsLogger

import com.klu.passwordlessauthentication.data.OtpManager
import com.klu.passwordlessauthentication.data.OtpValidationResult

class AuthViewModel : ViewModel() {

    private val otpManager = OtpManager()

    var uiState by mutableStateOf(AuthUiState())
        private set

    fun onEmailChange(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun sendOtp() {
        otpManager.generateOtp(uiState.email)
        uiState = uiState.copy(
            otpSent = true,
            error = null
        )
    }

    fun verifyOtp(otp: String) {
        when (val result = otpManager.validateOtp(uiState.email, otp)) {

            is OtpValidationResult.Success -> {
                uiState = uiState.copy(
                    isLoggedIn = true,
                    sessionStartTime = System.currentTimeMillis(),
                    error = null
                )
            }

            is OtpValidationResult.Expired -> {
                uiState = uiState.copy(
                    error = "OTP expired. Please resend OTP.",
                    otpResetKey = uiState.otpResetKey + 1
                )
            }

            is OtpValidationResult.AttemptsExceeded -> {
                uiState = uiState.copy(
                    error = "Maximum attempts exceeded. Please resend OTP.",
                    otpResetKey = uiState.otpResetKey + 1
                )
            }

            is OtpValidationResult.Incorrect -> {
                uiState = uiState.copy(
                    error = "Incorrect OTP. Attempts left: ${result.attemptsLeft}",
                    otpResetKey = uiState.otpResetKey + 1
                )
            }
        }
    }

    fun logout() {
        uiState = AuthUiState()
        AnalyticsLogger.logLogout();
    }
}
