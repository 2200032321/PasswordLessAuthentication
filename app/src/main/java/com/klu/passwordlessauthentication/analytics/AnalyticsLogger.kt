package com.klu.passwordlessauthentication.analytics

import timber.log.Timber

object AnalyticsLogger {

    fun logOtpGenerated() = Timber.d("OTP Generated")
    fun logOtp(otp:String, email:String) = Timber.d("OTP Generated | email=$email | otp=$otp")
    fun logOtpSuccess() = Timber.d("OTP Validation Success")
    fun logOtpFailure() = Timber.d("OTP Validation Failure")
    fun logLogout() = Timber.d("User Logged Out")
}
