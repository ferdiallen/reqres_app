package com.ferdialif.reqresapp.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ferdialif.reqresapp.R
import com.ferdialif.reqresapp.core.font.poppins
import com.ferdialif.reqresapp.core.util.ApiResponse
import com.ferdialif.reqresapp.domain.model.LoginModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val login by viewModel.loginStatus.collectAsState(ApiResponse.Loading())
    var shouldVisibleErrorDialog by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = login, block = {
        when (login) {
            is ApiResponse.Success -> {
                navigateToHomeScreen()
                viewModel.resetLoginStatus()
            }

            is ApiResponse.Error -> {
                shouldVisibleErrorDialog = true
            }

            is ApiResponse.Loading -> {

            }
        }
    })
    var shouldShowPassword by remember {
        mutableStateOf(false)
    }
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = viewModel::onEmailChange,
            textStyle = TextStyle(fontFamily = poppins),
            shape = CircleShape,
            label = {
                Text(text = stringResource(R.string.email), fontFamily = poppins)
            },
            maxLines = 1, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = password,
            onValueChange = viewModel::onPasswordChange,
            textStyle = TextStyle(fontFamily = poppins),
            shape = CircleShape,
            label = {
                Text(text = stringResource(R.string.password), fontFamily = poppins)
            },
            maxLines = 1,
            visualTransformation = if (!shouldShowPassword) PasswordVisualTransformation()
            else VisualTransformation.None, trailingIcon = {
                IconButton(onClick = { shouldShowPassword = !shouldShowPassword }) {
                    Icon(
                        imageVector = if (shouldShowPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = ""
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                viewModel.loginUser(LoginModel(email, password))
            }, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.07F)
        ) {
            Text(
                text = stringResource(R.string.login),
                fontFamily = poppins
            )
        }
    }
    CustomDialog(dismiss = { shouldVisibleErrorDialog = false }, isVisible = {
        shouldVisibleErrorDialog
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomDialog(dismiss: () -> Unit, isVisible: () -> Boolean) {
    if (isVisible()) {
        AlertDialog(onDismissRequest = { dismiss() }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.7F)
                    .fillMaxHeight(0.3F)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp)
                        .padding(horizontal = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Login Failed",
                        fontFamily = poppins,
                        style = TextStyle(fontSize = 24.sp), fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "The data you have submitted is not valid",
                        fontFamily = poppins,
                        style = TextStyle(fontSize = 15.sp),
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Button(onClick = { dismiss() }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Close",
                            fontFamily = poppins,
                            style = TextStyle(fontSize = 15.sp),
                            fontWeight = FontWeight.Light
                        )
                    }
                }
            }
        }

    }
}