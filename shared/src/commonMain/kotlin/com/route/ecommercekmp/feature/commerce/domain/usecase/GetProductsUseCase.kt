package com.route.ecommercekmp.feature.commerce.domain.usecase

import com.route.ecommercekmp.feature.commerce.domain.model.Product
import com.route.ecommercekmp.feature.commerce.domain.repositories.HomeRepository

class GetProductsUseCase(private val homeRepository: HomeRepository) {
    suspend operator fun invoke(categoryId: String? = null, subCategoryId: String? = null): Result<List<Product>> {
        return homeRepository.getProducts(categoryId, subCategoryId)
    }
}
