package com.route.ecommercekmp.feature.commerce.data.mappers

import com.route.ecommercekmp.feature.commerce.domain.model.Product
import com.route.ecommercekmp.feature.network.response.prouducts.RemoteProduct

fun RemoteProduct.toProduct(): Product = Product(
    sold = sold ?: 0,
    images = images?.filterNotNull() ?: emptyList(),
    quantity = quantity ?: 0,
    imageCover = imageCover ?: "",
    description = description ?: "",
    title = title ?: "",
    ratingsQuantity = ratingsQuantity ?: 0,
    ratingsAverage = ratingsAverage,
    price = price?.toDouble() ?: 0.0,
    id = id ?: "",
    priceAfterDiscount = priceAfterDiscount?.toDouble(),
    brandName = brand?.name ?: "",
    categoryName = category?.name ?: ""
)


