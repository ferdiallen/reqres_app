package com.ferdialif.reqresapp.domain.repository

import com.ferdialif.reqresapp.domain.api.Login
import com.ferdialif.reqresapp.domain.model.LoginError
import com.ferdialif.reqresapp.domain.model.LoginModel
import com.ferdialif.reqresapp.domain.model.LoginSuccess
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val login: Login
) {
    suspend fun login(
        data: LoginModel,
        onSuccess: (LoginSuccess?) -> Unit,
        onError: (LoginError?) -> Unit
    ) =
        login.loginUser(data, successCallback = {
            onSuccess(LoginSuccess(it))
        }, errorCallback = {
            onError(LoginError(it))
        })
}