package com.klu.passwordlessauthentication.data

sealed class OtpValidationResult {
    object Success : OtpValidationResult()
    object Expired : OtpValidationResult()
    object AttemptsExceeded : OtpValidationResult()
    data class Incorrect(val attemptsLeft: Int) : OtpValidationResult()
}
