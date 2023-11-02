package com.ferdialif.reqresapp.core.util

sealed class ApiResponse<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T?) : ApiResponse<T>(data = data)
    class Error<T>(data: T?, errorMessage: String?) : ApiResponse<T>(data = data, errorMessage)
    class Loading<T> : ApiResponse<T>(null, null)
}