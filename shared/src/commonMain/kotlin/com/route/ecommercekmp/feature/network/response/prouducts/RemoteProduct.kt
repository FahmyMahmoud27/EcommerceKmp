package com.route.ecommercekmp.feature.network.response.prouducts

import com.route.ecommercekmp.feature.network.response.categories.RemoteCategory
import kotlinx.serialization.Serializable

@Serializable
data class RemoteProduct(

    val sold: Int? = null,
    val images: List<String?>? = null,
    val quantity: Int? = null,
    val imageCover: String? = null,
    val description: String? = null,
    val title: String? = null,
    val ratingsQuantity: Int? = null,
    val ratingsAverage: Double? = null,
    val createdAt: String? = null,
    val price: Int? = null,
    val id: String? = null,
    val subcategory: List<RemoteCategory?>? = null,
    val category: RemoteCategory? = null,
    val priceAfterDiscount: Int? = null,
    val brand: RemoteCategory? = null,
    val slug: String? = null,
    val updatedAt: String? = null
)
