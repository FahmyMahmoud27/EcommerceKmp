package com.route.ecommercekmp.feature.commerce.domain.model

data class Cart(
    val products: Map<String, Product>,
    val totalPrice: Double

)
