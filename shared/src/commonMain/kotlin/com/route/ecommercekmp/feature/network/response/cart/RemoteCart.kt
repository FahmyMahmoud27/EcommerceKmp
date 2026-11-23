package com.route.ecommercekmp.feature.network.response.cart

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCart(
	val cartOwner: String? = null,
	val createdAt: String? = null,
	val totalCartPrice: Double? = null,
	val v: Int? = null,
	val id: String? = null,
	@SerialName("products")
	val remoteCartItems: List<RemoteCartItem?>? = null,
	val updatedAt: String? = null
)
