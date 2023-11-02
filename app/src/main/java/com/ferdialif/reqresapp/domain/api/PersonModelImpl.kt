package com.ferdialif.reqresapp.domain.api

import com.ferdialif.reqresapp.BuildConfig
import com.ferdialif.reqresapp.domain.model.PersonModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class PersonModelImpl @Inject constructor(
    private val client: HttpClient
) : PersonData {
    override suspend fun getPersonData(page: Int): PersonModel {
        return try {
            client.get("${BuildConfig.BASE_URL}api/users") {
                parameter("page", page)
                parameter("per_page", 3)
            }.body()
        } catch (e: Exception) {
            return PersonModel()
        }
    }
}