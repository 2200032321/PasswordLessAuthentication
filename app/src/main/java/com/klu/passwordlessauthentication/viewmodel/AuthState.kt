package com.klu.passwordlessauthentication.viewmodel

data class AuthUiState(
    val email: String = "",
    val otpSent: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: String? = null,
    val sessionStartTime: Long? = null,
    val otpResetKey: Int = 0
)
