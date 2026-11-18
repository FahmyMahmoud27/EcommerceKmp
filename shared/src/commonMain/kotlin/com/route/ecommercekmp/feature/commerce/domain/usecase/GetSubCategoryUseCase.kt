package com.route.ecommercekmp.feature.commerce.domain.usecase

import com.route.ecommercekmp.feature.commerce.domain.model.Category
import com.route.ecommercekmp.feature.commerce.domain.repositories.HomeRepository



class GetSubCategoriesUseCase(private val homeRepository: HomeRepository) {
    suspend operator fun invoke(categoryId: String): Result<List<Category>> {
        return homeRepository.getSubCategories(categoryId)
    }
}
