package com.route.ecommercekmp.feature.commerce.domain.usecase

import com.route.ecommercekmp.feature.commerce.domain.model.Product
import com.route.ecommercekmp.feature.commerce.domain.repositories.HomeRepository

class GetProductByIdUseCase(private val homeRepository: HomeRepository) {
    suspend operator fun invoke(productId: String): Result<Product?> {
        return homeRepository.getProductById(productId)
    }
}
