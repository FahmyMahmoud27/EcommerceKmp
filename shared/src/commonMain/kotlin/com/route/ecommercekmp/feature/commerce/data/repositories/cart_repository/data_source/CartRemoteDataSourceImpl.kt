package com.route.ecommercekmp.feature.commerce.data.repositories.cart_repository.data_source

import com.route.ecommercekmp.feature.network.request.AddToCartRequest
import com.route.ecommercekmp.feature.network.request.UpdateCartRequest
import com.route.ecommercekmp.feature.network.response.cart.CartResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class CartRemoteDataSourceImpl(val httpClient: HttpClient) : CartRemoteDataSource {
    val cartUrl = "v1/cart"
    override suspend fun addToCart(productId: String?): Result<CartResponse> {
        try {
            val response = httpClient.post(cartUrl) {
                setBody(AddToCartRequest(productId))
            }
            return getCart()
        } catch (t: Throwable) {
            return Result.failure(t)
        }
    }

    override suspend fun getCart(): Result<CartResponse> {
        try {
            val response = httpClient.get(cartUrl)
            val cartResponse = response.body<CartResponse>()
            return Result.success(cartResponse)
        } catch (t: Throwable) {
            return Result.failure(t)
        }
    }

    override suspend fun removeFromCart(productId: String?): Result<CartResponse> {
        try {
            val response = httpClient.delete("$cartUrl/$productId")
            val cartResponse = response.body<CartResponse>()
            return Result.success(cartResponse)
        } catch (t: Throwable) {
            return Result.failure(t)
        }
    }

    override suspend fun updateQuantity(
        productId: String?,
        quantity: Int
    ): Result<CartResponse> {
        try {
            val response = httpClient.put("$cartUrl/$productId") {
                setBody(UpdateCartRequest(quantity))
            }
            val cartResponse = response.body<CartResponse>()
            return Result.success(cartResponse)
        } catch (t: Throwable) {
            return Result.failure(t)
        }
    }

}

