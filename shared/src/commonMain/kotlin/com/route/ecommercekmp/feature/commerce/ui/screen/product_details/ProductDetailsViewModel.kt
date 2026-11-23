package com.route.ecommercekmp.feature.commerce.ui.screen.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetProductByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {

    val state: MutableStateFlow<ProductDetailsState> =
        MutableStateFlow(ProductDetailsState())

    fun doAction(event: ProductDetailsEvents) {
        when (event) {
            is ProductDetailsEvents.LoadProduct -> loadProduct(event.productId)
        }
    }

    private fun loadProduct(productId: String) {
        state.value = state.value.copy(productApi = Resource.Loading)
        viewModelScope.launch {
            val result = getProductByIdUseCase.invoke(productId)
            val product = result.getOrNull()
            if (result.isSuccess && product != null) {
                state.value = state.value.copy(productApi = Resource.Success(product))
            } else {
                state.value = state.value.copy(
                    productApi = Resource.Error(
                        result.exceptionOrNull() ?: Exception("Product not found")
                    )
                )
            }
        }
    }
}
