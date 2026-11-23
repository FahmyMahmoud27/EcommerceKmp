package com.route.ecommercekmp.feature.commerce.data.repositories.wishlist_repository.data_source

import com.route.ecommercekmp.feature.network.response.wishlist.WishlistActionResponse
import com.route.ecommercekmp.feature.network.response.wishlist.WishlistResponse

interface WishlistRemoteDataSource {
    suspend fun getWishlist(): Result<WishlistResponse>
    suspend fun addToWishlist(productId: String?): Result<WishlistActionResponse>
    suspend fun removeFromWishlist(productId: String?): Result<WishlistActionResponse>
}
