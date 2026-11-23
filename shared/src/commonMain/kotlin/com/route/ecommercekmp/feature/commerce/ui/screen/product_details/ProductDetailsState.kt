package com.route.ecommercekmp.feature.commerce.ui.screen.product_details

import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.model.Product

data class ProductDetailsState(
    val productApi: Resource<Product> = Resource.Idle
)

sealed class ProductDetailsEvents {
    data class LoadProduct(val productId: String) : ProductDetailsEvents()
}
