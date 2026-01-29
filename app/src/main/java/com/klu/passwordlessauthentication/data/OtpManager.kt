package com.klu.passwordlessauthentication.data

import com.klu.passwordlessauthentication.analytics.AnalyticsLogger

class OtpManager {

    private val otpStore = mutableMapOf<String, OtpData>()
    private val expiryMillis = 60_000L

    fun generateOtp(email: String): String {
        val otp = (100000..999999).random().toString()
        otpStore[email] = OtpData(
            otp = otp,
            createdAt = System.currentTimeMillis(),
            attemptsLeft = 3
        )
        AnalyticsLogger.logOtpGenerated()
        AnalyticsLogger.logOtp(otp, email)
        return otp
    }

    fun validateOtp(email: String, enteredOtp: String): OtpValidationResult {
        val data = otpStore[email] ?: return OtpValidationResult.Expired

        if (System.currentTimeMillis() - data.createdAt > expiryMillis) {
            otpStore.remove(email)
            return OtpValidationResult.Expired
        }

        if (data.attemptsLeft <= 0) {
            otpStore.remove(email)
            return OtpValidationResult.AttemptsExceeded
        }

        if (data.otp == enteredOtp) {
            AnalyticsLogger.logOtpSuccess()
            otpStore.remove(email)
            return OtpValidationResult.Success
        }

        data.attemptsLeft--
        AnalyticsLogger.logOtpFailure()
        return OtpValidationResult.Incorrect(data.attemptsLeft)
    }
}
