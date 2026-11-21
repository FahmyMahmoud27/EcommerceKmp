package com.route.ecommercekmp.feature.commerce.ui.screen.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(private val productsUseCase: GetProductsUseCase) : ViewModel() {
    val state: MutableStateFlow<ProductsState> = MutableStateFlow(ProductsState())


    fun doAction(event: ProductsEvents) {
        when (event) {
            is ProductsEvents.LoadProducts -> {
                loadProducts(event.category, event.subCategory)
            }
        }
    }

    private fun loadProducts(category: String, subCategory: String) {
        state.value = state.value.copy(productsApiState = Resource.Loading)
        viewModelScope.launch {
            val result = productsUseCase.invoke()
            if (result.isSuccess) {
                state.value =
                    state.value.copy(productsApiState = Resource.Success(result.getOrNull()))
            } else {
                state.value =
                    state.value.copy(productsApiState = Resource.Error(result.exceptionOrNull()))
            }

        }
    }

}

