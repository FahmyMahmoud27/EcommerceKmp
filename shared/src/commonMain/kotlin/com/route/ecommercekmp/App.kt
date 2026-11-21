package com.route.ecommercekmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.route.ecommercekmp.feature.auth.ui.screens.login.LoginView
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.MainBottomNavView
import com.route.ecommercekmp.feature.commerce.ui.screen.products.ProductView
import com.route.ecommercekmp.feature.commerce.ui.screen.splash.SplashView


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
        NavHost(navController = navController, startDestination = AppRoutes.SplashRoute) {

            composable<AppRoutes.SplashRoute>() {
                SplashView(navController)
            }

            composable<AppRoutes.MainBottomNavRoute>() {
                MainBottomNavView(navController)
            }

            composable<AppRoutes.LoginRoute>() {
                LoginView(navController)
            }

            composable<AppRoutes.ProductsRoute>() {backStackEntry ->
                val route = backStackEntry.toRoute<AppRoutes.ProductsRoute>()
                ProductView(navController, route.categoryId, route.subCategoryId)
            }
            composable<AppRoutes.LoginRoute>() {
                LoginView(navController)
            }

        }

    }



}

