package com.route.ecommercekmp

import kotlinx.serialization.Serializable

sealed class AppRoutes{
    @Serializable
    object LoginRoute


    @Serializable
    object MainBottomNavRoute

    @Serializable
    object SplashRoute

    @Serializable
    data class ProductsRoute(val categoryId: String, val subCategoryId: String?)

    @Serializable
    data class ProductDetailsRoute(val productId: String)

    @Serializable
    object CartRoute

}