package com.klu.passwordlessauthentication.data

data class OtpData(
    val otp: String,
    val createdAt: Long,
    var attemptsLeft: Int
)
