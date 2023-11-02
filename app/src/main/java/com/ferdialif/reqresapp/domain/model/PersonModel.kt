package com.ferdialif.reqresapp.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonModel(
    @SerialName("data")
    val data: List<Data> = emptyList(),
    @SerialName("page")
    val page: Int = 0,
    @SerialName("per_page")
    val perPage: Int = 0,
    @SerialName("support")
    val support: Support? = null,
    @SerialName("total")
    val total: Int = 0,
    @SerialName("total_pages")
    val totalPages: Int = 0
)