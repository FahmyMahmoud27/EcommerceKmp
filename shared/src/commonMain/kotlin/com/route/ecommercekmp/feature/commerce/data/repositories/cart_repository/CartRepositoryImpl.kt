package com.route.ecommercekmp.feature.commerce.data.repositories.cart_repository

import com.route.ecommercekmp.feature.commerce.data.mappers.toCart
import com.route.ecommercekmp.feature.commerce.data.repositories.cart_repository.data_source.CartRemoteDataSource
import com.route.ecommercekmp.feature.commerce.domain.model.Cart
import com.route.ecommercekmp.feature.commerce.domain.repositories.CartRepository
import com.route.ecommercekmp.feature.common.utils.defaultException

class CartRepositoryImpl(private val cartRemoteDataSource: CartRemoteDataSource) : CartRepository {
    override suspend fun addToCart(productId: String?): Result<Cart> {
        val result = cartRemoteDataSource.addToCart(productId)
        val cartResponse = result.getOrNull()
        return if (result.isSuccess && cartResponse != null && cartResponse.cart != null) {

            Result.success(cartResponse.cart.toCart())
        } else {
            Result.failure(result.exceptionOrNull() ?: defaultException)
        }
    }

    override suspend fun getCart(): Result<Cart> {
        val result = cartRemoteDataSource.getCart()
        val cartResponse = result.getOrNull()
        return if (result.isSuccess && cartResponse != null && cartResponse.cart != null) {

            Result.success(cartResponse.cart.toCart())
        } else {
            Result.failure(result.exceptionOrNull() ?: defaultException)
        }
    }

    override suspend fun removeFromCart(productId: String?): Result<Cart> {
        val result = cartRemoteDataSource.removeFromCart(productId)
        val cartResponse = result.getOrNull()
        return if (result.isSuccess && cartResponse != null && cartResponse.cart != null) {

            Result.success(cartResponse.cart.toCart())
        } else {
            Result.failure(result.exceptionOrNull() ?: defaultException)
        }
    }

    override suspend fun updateQuantity(
        productId: String?,
        quantity: Int
    ): Result<Cart> {
        val result = cartRemoteDataSource.updateQuantity(productId, quantity)
        val cartResponse = result.getOrNull()
        return if (result.isSuccess && cartResponse != null && cartResponse.cart != null) {

            Result.success(cartResponse.cart.toCart())
        } else {
            Result.failure(result.exceptionOrNull() ?: defaultException)
        }
    }

}