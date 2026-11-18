package com.route.ecommercekmp.feature.network.response.prouducts

import com.route.ecommercekmp.feature.network.response.Metadata
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProductsResponse(
	val metadata: Metadata? = null,
	@SerialName("data")
	val products: List<RemoteProduct>? = null,
	val results: Int? = null
)
