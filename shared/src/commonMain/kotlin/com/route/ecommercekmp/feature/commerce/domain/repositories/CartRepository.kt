package com.route.ecommercekmp.feature.commerce.domain.repositories

import com.route.ecommercekmp.feature.commerce.domain.model.Cart

interface CartRepository {
    suspend fun addToCart(productId: String?): Result<Cart>
    suspend fun getCart(): Result<Cart>
    suspend fun removeFromCart(productId: String?): Result<Cart>
    suspend fun updateQuantity(productId: String?, quantity: Int): Result<Cart>
}

