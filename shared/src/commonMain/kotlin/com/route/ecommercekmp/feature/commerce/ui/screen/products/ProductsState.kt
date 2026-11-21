package com.route.ecommercekmp.feature.commerce.ui.screen.products

import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.model.Product


data class ProductsState(
    val productsApiState: Resource<List<Product>> = Resource.Idle
)


sealed class ProductsEvents {
    data class LoadProducts(val category: String, val subCategory: String) : ProductsEvents()
}

