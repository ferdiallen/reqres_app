package com.ferdialif.reqresapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginModel(
    val email: String = "",
    val password: String = ""
)

@Serializable
data class LoginSuccess(
    val token: String = ""
)

@Serializable
data class LoginError(
    val error: String = ""
)