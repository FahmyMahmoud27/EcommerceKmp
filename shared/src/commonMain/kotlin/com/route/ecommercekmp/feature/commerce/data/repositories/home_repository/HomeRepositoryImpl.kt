package com.route.ecommercekmp.feature.commerce.data.repositories.home_repository

import com.route.ecommercekmp.feature.commerce.data.mappers.toCategory
import com.route.ecommercekmp.feature.commerce.data.mappers.toProduct
import com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.data_source.HomeRemoteDataSource
import com.route.ecommercekmp.feature.commerce.domain.model.Category
import com.route.ecommercekmp.feature.commerce.domain.model.Product
import com.route.ecommercekmp.feature.commerce.domain.repositories.HomeRepository
import com.route.ecommercekmp.feature.common.utils.defaultException
import com.route.ecommercekmp.feature.network.response.categories.CategoriesResponse

class HomeRepositoryImpl(private val homeRemoteDataSource: HomeRemoteDataSource) : HomeRepository {

    override suspend fun getCategories(): Result<List<Category>> {
        val result = homeRemoteDataSource.getCategories()
        return if (result.isSuccess && result.getOrNull() != null) {
            val categoriesResponse: CategoriesResponse = result.getOrNull()!!
            val categories = categoriesResponse.categories?.map { remoteCategory ->
                return@map remoteCategory.toCategory()
            } ?: emptyList()

            Result.success(categories)
        } else {
            Result.failure(result.exceptionOrNull() ?: defaultException)
        }
    }

    override suspend fun getProducts(
        categoryId: String?,
        subCategoryId: String?
    ): Result<List<Product>> {
        val result = homeRemoteDataSource.getProducts(categoryId, subCategoryId)
        return if (result.isSuccess && result.getOrNull() != null) {
            val productsResponse = result.getOrNull()!!
            val products = productsResponse.products?.mapNotNull { it?.toProduct() } ?: emptyList()
            Result.success(products)
        } else {
            Result.failure(result.exceptionOrNull() ?: defaultException)
        }
    }

    override suspend fun getSubCategories(category: String): Result<List<Category>> {
        val result = homeRemoteDataSource.getSubCategories(category)
        return if (result.isSuccess && result.getOrNull() != null) {
            val categoriesResponse = result.getOrNull()!!
            val categories =
                categoriesResponse.categories?.mapNotNull { it.toCategory() } ?: emptyList()
            Result.success(categories)
        } else {
            Result.failure(result.exceptionOrNull() ?: defaultException)
        }
    }


}
