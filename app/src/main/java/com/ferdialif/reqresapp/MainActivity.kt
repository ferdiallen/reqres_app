package com.ferdialif.reqresapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ferdialif.reqresapp.core.navigation.Navigation
import com.ferdialif.reqresapp.presentation.home.HomeScreen
import com.ferdialif.reqresapp.presentation.home.ItemPager
import com.ferdialif.reqresapp.presentation.login.LoginScreen
import com.ferdialif.reqresapp.ui.theme.ReqresAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReqresAppTheme {
                Navigation()
            }
        }
    }
}
