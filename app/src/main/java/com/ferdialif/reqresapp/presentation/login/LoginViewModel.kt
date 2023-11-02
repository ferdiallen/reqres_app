package com.ferdialif.reqresapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferdialif.reqresapp.core.util.ApiResponse
import com.ferdialif.reqresapp.domain.model.LoginModel
import com.ferdialif.reqresapp.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _loginStatus = MutableStateFlow<ApiResponse<String>>(ApiResponse.Loading())
    val loginStatus = _loginStatus.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onEmailChange(data: String) = _email.update { data }
    fun onPasswordChange(data: String) = _password.update { data }

    fun loginUser(data: LoginModel) = viewModelScope.launch(Dispatchers.IO) {
        loginRepository.login(data, onSuccess = { token ->
            _loginStatus.update {
                ApiResponse.Success(token?.token)
            }
        }, onError = { error ->
            _loginStatus.update {
                ApiResponse.Error(errorMessage = error?.error, data = null)
            }
        })
    }

    fun resetLoginStatus() {
        _loginStatus.update {
            ApiResponse.Loading()
        }
        _email.update { "" }
        _password.update { "" }
    }
}