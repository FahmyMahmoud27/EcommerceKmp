package com.route.ecommercekmp.feature.network.response.cart

import com.route.ecommercekmp.feature.network.response.prouducts.RemoteProduct
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCartItem(
	val product: RemoteProduct? = null,
	val price: Int? = null,
	val count: Int? = null,
	@SerialName("_id")
	val id: String? = null
)
