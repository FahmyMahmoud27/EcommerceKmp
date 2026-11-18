package com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.data_source

import com.route.ecommercekmp.feature.network.response.categories.CategoriesResponse
import com.route.ecommercekmp.feature.network.response.prouducts.ProductsResponse

interface HomeRemoteDataSource {
   suspend fun getCategories(): Result<CategoriesResponse>
   suspend fun getProducts(categoryId: String?, subCategoryId: String?): Result<ProductsResponse>
   suspend fun getSubCategories(categoryId: String): Result<CategoriesResponse>
}

