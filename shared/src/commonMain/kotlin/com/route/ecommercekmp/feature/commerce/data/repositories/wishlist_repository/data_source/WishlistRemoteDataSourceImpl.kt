package com.route.ecommercekmp.feature.commerce.data.repositories.wishlist_repository.data_source

import com.route.ecommercekmp.feature.network.request.AddToWishlistRequest
import com.route.ecommercekmp.feature.network.response.wishlist.WishlistActionResponse
import com.route.ecommercekmp.feature.network.response.wishlist.WishlistResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class WishlistRemoteDataSourceImpl(private val httpClient: HttpClient) : WishlistRemoteDataSource {
    private val wishlistUrl = "v1/wishlist"

    override suspend fun getWishlist(): Result<WishlistResponse> {
        try {
            val response = httpClient.get(wishlistUrl)
            return Result.success(response.body<WishlistResponse>())
        } catch (t: Throwable) {
            return Result.failure(t)
        }
    }

    override suspend fun addToWishlist(productId: String?): Result<WishlistActionResponse> {
        try {
            val response = httpClient.post(wishlistUrl) {
                setBody(AddToWishlistRequest(productId))
            }
            return Result.success(response.body<WishlistActionResponse>())
        } catch (t: Throwable) {
            return Result.failure(t)
        }
    }

    override suspend fun removeFromWishlist(productId: String?): Result<WishlistActionResponse> {
        try {
            val response = httpClient.delete("$wishlistUrl/$productId")
            return Result.success(response.body<WishlistActionResponse>())
        } catch (t: Throwable) {
            return Result.failure(t)
        }
    }
}
