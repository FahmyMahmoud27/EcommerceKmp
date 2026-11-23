package com.route.ecommercekmp.feature.network.request

import kotlinx.serialization.Serializable


@Serializable
data class AddToCartRequest (val productId: String?)