package com.route.ecommercekmp.feature.commerce.data.repositories.wishlist_repository

import com.route.ecommercekmp.feature.commerce.data.mappers.toProducts
import com.route.ecommercekmp.feature.commerce.data.repositories.wishlist_repository.data_source.WishlistRemoteDataSource
import com.route.ecommercekmp.feature.commerce.domain.model.Product
import com.route.ecommercekmp.feature.commerce.domain.repositories.WishlistRepository
import com.route.ecommercekmp.feature.common.utils.defaultException

class WishlistRepositoryImpl(
    private val wishlistRemoteDataSource: WishlistRemoteDataSource
) : WishlistRepository {

    override suspend fun getWishlist(): Result<List<Product>> {
        val result = wishlistRemoteDataSource.getWishlist()
        val response = result.getOrNull()
        return if (result.isSuccess && response != null) {
            Result.success(response.toProducts())
        } else {
            Result.failure(result.exceptionOrNull() ?: defaultException)
        }
    }

    override suspend fun addToWishlist(productId: String?): Result<List<String>> {
        val result = wishlistRemoteDataSource.addToWishlist(productId)
        val response = result.getOrNull()
        return if (result.isSuccess && response != null) {
            Result.success(response.data.orEmpty())
        } else {
            Result.failure(result.exceptionOrNull() ?: defaultException)
        }
    }

    override suspend fun removeFromWishlist(productId: String?): Result<List<String>> {
        val result = wishlistRemoteDataSource.removeFromWishlist(productId)
        val response = result.getOrNull()
        return if (result.isSuccess && response != null) {
            Result.success(response.data.orEmpty())
        } else {
            Result.failure(result.exceptionOrNull() ?: defaultException)
        }
    }
}
