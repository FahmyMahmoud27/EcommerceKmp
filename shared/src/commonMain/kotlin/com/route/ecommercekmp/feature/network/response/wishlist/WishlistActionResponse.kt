package com.route.ecommercekmp.feature.network.response.wishlist

import kotlinx.serialization.Serializable

@Serializable
data class WishlistActionResponse(
    val status: String? = null,
    val message: String? = null,
    val data: List<String>? = null
)
