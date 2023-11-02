package com.ferdialif.reqresapp.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("avatar")
    val avatar: String = "",
    @SerialName("email")
    val email: String = "",
    @SerialName("first_name")
    val firstName: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("last_name")
    val lastName: String = ""
)