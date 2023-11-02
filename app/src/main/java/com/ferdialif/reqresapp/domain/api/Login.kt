package com.ferdialif.reqresapp.domain.api

import com.ferdialif.reqresapp.domain.model.LoginModel

interface Login {
    suspend fun loginUser(
        data: LoginModel,
        successCallback: (String) -> Unit,
        errorCallback: (String) -> Unit
    )
}