package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab

import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.model.Category
import com.route.ecommercekmp.feature.commerce.domain.model.Product


data class HomeTabState(
    val categoryApi: Resource<List<Category>> = Resource.Loading,
    val productApi: Resource<List<Product>> = Resource.Loading,
    val subCategoriesApi: Resource<List<Category>> = Resource.Idle
)

sealed class HomeTabEvents {
    object LoadData : HomeTabEvents()

    data class LoadSubCategories(val category: Category): HomeTabEvents()
    object LoadCategories : HomeTabEvents()
    object LoadProducts : HomeTabEvents()


}

