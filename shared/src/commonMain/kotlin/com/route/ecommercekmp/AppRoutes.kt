package com.route.ecommercekmp

import kotlinx.serialization.Serializable

sealed class AppRoutes{
    @Serializable
    object LoginRoute
}