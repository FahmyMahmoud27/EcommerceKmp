package com.route.ecommercekmp

import kotlinx.serialization.Serializable

sealed class AppRoutes{
    @Serializable
    object LoginRoute


    @Serializable
    object MainBottomNav

    @Serializable
    object SplashRoute

}