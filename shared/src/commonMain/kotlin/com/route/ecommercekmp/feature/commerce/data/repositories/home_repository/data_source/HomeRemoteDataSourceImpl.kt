package com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.data_source

import com.route.ecommercekmp.feature.network.response.categories.CategoriesResponse
import com.route.ecommercekmp.feature.network.response.prouducts.ProductResponse
import com.route.ecommercekmp.feature.network.response.prouducts.ProductsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class HomeRemoteDataSourceImpl(private val httpClient: HttpClient) : HomeRemoteDataSource {
    override suspend fun getCategories(): Result<CategoriesResponse> {

        try {
            val response = httpClient.get("v1/categories")
            val categoriesResponse = response.body<CategoriesResponse>()
            return Result.success(categoriesResponse)
        } catch (t: Throwable) {
            return Result.failure(t)
        }

    }

    override suspend fun getProducts(
        categoryId: String?,
        subCategoryId: String?
    ): Result<ProductsResponse> {
        try {
            var url = "v1/products${if (categoryId != null || subCategoryId != null) "?" else ""}"
            if (categoryId != null) {
                url += "category=$categoryId"
            }
            if (subCategoryId != null) {
                url += "${if (categoryId != null) "&" else ""}sub)}category=$subCategoryId"
            }

            val response = httpClient.get(url)
            val productsResponse = response.body<ProductsResponse>()
            return Result.success(productsResponse)
        } catch (t: Throwable) {
            return Result.failure(t)
        }
    }

    override suspend fun getProductById(productId: String): Result<ProductResponse> {
        try {
            val response = httpClient.get("v1/products/$productId")
            val productResponse = response.body<ProductResponse>()
            return Result.success(productResponse)
        } catch (t: Throwable) {
            return Result.failure(t)
        }
    }

    override suspend fun getSubCategories(categoryId: String): Result<CategoriesResponse> {
        try {
            val response = httpClient.get("v1/categories/${categoryId}/subcategories")
            val categoriesResponse = response.body<CategoriesResponse>()
            return Result.success(categoriesResponse)
        } catch (t: Throwable) {
            return Result.failure(t)
        }
    }


}


