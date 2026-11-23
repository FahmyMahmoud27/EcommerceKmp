package com.route.ecommercekmp.feature.commerce.domain.model

import com.route.ecommercekmp.feature.network.response.categories.RemoteCategory
import kotlin.Int

data class Product(

    val sold: Int = 0,
    val images: List<String> = emptyList(),
    val quantity: Int = 0,
    val imageCover: String = "",
    val description: String = "",
    val title: String = "",
    val ratingsQuantity: Int = 0,
    val ratingsAverage: Double? = null,
    val price: Double? = 0.0,
    val id: String? = null,
    val priceAfterDiscount: Double? = null,
    val brandName: String = "",
    val categoryName: String = "",
    var cartQuantity: Int = 0
)



