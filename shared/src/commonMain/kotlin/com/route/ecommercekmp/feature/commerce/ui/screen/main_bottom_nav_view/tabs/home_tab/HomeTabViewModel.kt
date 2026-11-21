package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.model.Category
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetCategoriesUseCase
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetProductsUseCase
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetSubCategoriesUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeTabViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getSubCategoriesUseCase: GetSubCategoriesUseCase
) : ViewModel() {
    val state: MutableStateFlow<HomeTabState> = MutableStateFlow(HomeTabState())


    fun doAction(event: HomeTabEvents) {
        when (event) {
            is HomeTabEvents.LoadData -> {
                loadData()
            }

            HomeTabEvents.LoadCategories -> {} //loadCategories()
            HomeTabEvents.LoadProducts -> {} //loadProducts()
            is HomeTabEvents.LoadSubCategories -> {
                loadSubCategories(event.category)
            }
        }


    }

    private fun loadSubCategories(category: Category) {
        viewModelScope.launch {
            state.value = state.value.copy(subCategoriesApi = Resource.Loading)
            val result = getSubCategoriesUseCase.invoke(category.id.orEmpty())
            if (result.isSuccess) {
                state.value =
                    state.value.copy(subCategoriesApi = Resource.Success(result.getOrNull()))

            } else {
                state.value =
                    state.value.copy(subCategoriesApi = Resource.Error(result.exceptionOrNull()))
            }
        }
    }

    private fun loadData() {
        state.value =
            state.value.copy(categoryApi = Resource.Loading, productApi = Resource.Loading)
        viewModelScope.launch {
            val productsRequest = async {
                getProductsUseCase.invoke()
            }
            val categoriesRequest = async {
                getCategoriesUseCase.invoke()
            }
            val productsResult = productsRequest.await()
            val categoriesResult = categoriesRequest.await()

            if (productsResult.isSuccess) {
                state.value =
                    state.value.copy(productApi = Resource.Success(productsResult.getOrNull()))
            } else {
                state.value =
                    state.value.copy(productApi = Resource.Error(productsResult.exceptionOrNull()))
            }
            if (categoriesResult.isSuccess) {
                state.value =
                    state.value.copy(categoryApi = Resource.Success(categoriesResult.getOrNull()))
                println("categories: ${categoriesResult.getOrNull()}")
            } else {
                state.value =
                    state.value.copy(categoryApi = Resource.Error(categoriesResult.exceptionOrNull()))
            }
        }

    }

    private fun loadProducts() {

    }

    private fun loadCategories() {}
}


