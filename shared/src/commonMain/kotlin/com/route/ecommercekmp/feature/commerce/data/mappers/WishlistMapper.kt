package com.route.ecommercekmp.feature.commerce.data.mappers

import com.route.ecommercekmp.feature.commerce.domain.model.Product
import com.route.ecommercekmp.feature.network.response.wishlist.WishlistResponse

fun WishlistResponse.toProducts(): List<Product> =
    products.orEmpty().filterNotNull().map { it.toProduct() }
