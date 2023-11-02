package com.ferdialif.reqresapp.core.navigation

sealed class NavigationIdentifier(val route: String) {
    data object HomeScreen : NavigationIdentifier("home_screen")
    data object LoginScreen : NavigationIdentifier("login_screen")
}