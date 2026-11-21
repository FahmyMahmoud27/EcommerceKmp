package com.route.ecommercekmp.feature.network.response.categories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RemoteCategory(
	val image: String? = null,
	val createdAt: String? = null,
	val name: String? = null,
	val category: String? = null,
	@SerialName("_id")
	val id: String? = null,
	val slug: String? = null,
	val updatedAt: String? = null
)
