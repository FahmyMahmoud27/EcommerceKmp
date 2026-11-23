package com.route.ecommercekmp.feature.commerce.domain.repositories

import com.route.ecommercekmp.feature.commerce.domain.model.Product

interface WishlistRepository {
    suspend fun getWishlist(): Result<List<Product>>
    suspend fun addToWishlist(productId: String?): Result<List<String>>
    suspend fun removeFromWishlist(productId: String?): Result<List<String>>
}
