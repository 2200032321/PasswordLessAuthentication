package com.klu.passwordlessauthentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.klu.passwordlessauthentication.ui.LoginScreen
import com.klu.passwordlessauthentication.ui.OtpScreen
import com.klu.passwordlessauthentication.ui.SessionScreen
import com.klu.passwordlessauthentication.viewmodel.AuthViewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        Timber.d("Launched main")
        enableEdgeToEdge()

        setContent {
            val authViewModel: AuthViewModel = viewModel()
            val uiState = authViewModel.uiState

            Scaffold { innerPadding ->
                when {
                    !uiState.otpSent -> {
                        LoginScreen(
                            email = uiState.email,
                            onEmailChange = { email ->
                                authViewModel.onEmailChange(email)
                            },
                            onSendOtpClick = {
                                authViewModel.sendOtp()
                            },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                    uiState.otpSent && !uiState.isLoggedIn -> {
                        OtpScreen(
                            error = uiState.error,
                            otpResetKey = uiState.otpResetKey,
                            onVerifyClick = { otp ->
                                authViewModel.verifyOtp(otp)
                            },
                            onResendClick = {
                                authViewModel.sendOtp()
                            },
                            modifier = Modifier.padding(innerPadding)
                        )

                    }

                    uiState.isLoggedIn -> {
                        SessionScreen(
                            sessionStartTime = uiState.sessionStartTime!!,
                            onLogoutClick = {
                                authViewModel.logout()
                            },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }

        }
        }
    }

