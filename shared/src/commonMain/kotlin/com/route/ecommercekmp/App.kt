package com.route.ecommercekmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.route.ecommercekmp.feature.auth.ui.screens.login.LoginView


@Composable

fun App() {

//    LaunchedEffect(Unit) {
//        val response = httpClient.post("v1/auth/signin") {
//            setBody(LoginRequest("fahmy@gmail.com", "Ahmed@123"))
//        }
//        println("Fahmy response ${response.body<AuthResponse>()}")
//    }


    val navController = rememberNavController()
    MaterialTheme {
        NavHost(navController = navController, startDestination = AppRoutes.LoginRoute) {
            composable<AppRoutes.LoginRoute>() {
                LoginView()
            }
        }

    }



}

