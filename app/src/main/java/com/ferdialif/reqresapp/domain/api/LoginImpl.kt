package com.ferdialif.reqresapp.domain.api

import com.ferdialif.reqresapp.BuildConfig
import com.ferdialif.reqresapp.domain.model.LoginModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class LoginImpl @Inject constructor(
    private val client: HttpClient
) : Login {
    override suspend fun loginUser(
        data: LoginModel,
        successCallback: (String) -> Unit,
        errorCallback: (String) -> Unit
    ) {
        val res = try {
            client.post("${BuildConfig.BASE_URL}api/login") {
                setBody(data)
                contentType(ContentType.Application.Json)
            }
        } catch (e: Exception) {
            errorCallback(e.localizedMessage ?: "")
            return
        }
        if (res.status.value in 200..299) {
            successCallback(res.body())
        } else if (res.status.value in 400..499) {
            errorCallback(res.status.description)
        }
    }
}