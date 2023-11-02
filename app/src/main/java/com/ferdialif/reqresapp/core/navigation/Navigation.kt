package com.ferdialif.reqresapp.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ferdialif.reqresapp.presentation.home.HomeScreen
import com.ferdialif.reqresapp.presentation.login.LoginScreen

@Composable
fun Navigation(controller: NavHostController = rememberNavController()) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = controller,
        startDestination = NavigationIdentifier.LoginScreen.route
    ) {
        composable(NavigationIdentifier.LoginScreen.route) {
            LoginScreen(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp), navigateToHomeScreen = {
                controller.navigate(NavigationIdentifier.HomeScreen.route)
            })
        }

        composable(NavigationIdentifier.HomeScreen.route, enterTransition = {
            slideInHorizontally(
                tween(400), initialOffsetX = {
                    it / 2
                }
            )
        }, exitTransition = {
            slideOutHorizontally(tween(400), targetOffsetX = {
                it + 10
            })
        }) {
            HomeScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 12.dp)
            )
        }
    }
}