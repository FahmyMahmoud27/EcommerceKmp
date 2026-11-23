package com.route.ecommercekmp.feature.network.response.wishlist

import com.route.ecommercekmp.feature.network.response.prouducts.RemoteProduct
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WishlistResponse(
    val status: String? = null,
    val count: Int? = null,
    @SerialName("data")
    val products: List<RemoteProduct?>? = null
)
