package com.route.ecommercekmp.feature.commerce.domain.repositories

import com.route.ecommercekmp.feature.commerce.domain.model.Category
import com.route.ecommercekmp.feature.commerce.domain.model.Product


interface HomeRepository {
    suspend fun getCategories(): Result<List<Category>>
    suspend fun getProducts(categoryId: String?, subCategoryId: String?): Result<List<Product>>
    suspend fun getSubCategories(category: String): Result<List<Category>>

}


