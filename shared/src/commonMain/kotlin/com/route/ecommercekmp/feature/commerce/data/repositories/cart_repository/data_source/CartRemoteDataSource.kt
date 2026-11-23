package com.route.ecommercekmp.feature.commerce.data.repositories.cart_repository.data_source

import com.route.ecommercekmp.feature.network.response.cart.CartResponse

interface CartRemoteDataSource {
  suspend fun addToCart(productId: String?): Result<CartResponse>
  suspend fun getCart(): Result<CartResponse>
  suspend fun removeFromCart(productId: String?): Result<CartResponse>
  suspend fun updateQuantity(productId: String?, quantity: Int): Result<CartResponse>
}


