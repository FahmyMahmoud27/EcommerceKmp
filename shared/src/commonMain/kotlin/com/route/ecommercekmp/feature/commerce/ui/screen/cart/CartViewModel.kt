package com.route.ecommercekmp.feature.commerce.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.repositories.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {
    val state: MutableStateFlow<CartState> = MutableStateFlow(CartState())


    fun doAction(event: CartEvents) {
        when (event) {
            is CartEvents.GetCart -> {
                getCart()
            }

            is CartEvents.AddToCart -> {
                addToCart(event.productId)
            }

            is CartEvents.RemoveFromCart -> {
                removeFromCart(event.productId)
            }

            is CartEvents.UpdateQuantity -> {
                updateQuantity(event.productId, event.quantity)
            }
        }

    }


    private fun removeFromCart(productId: String?) {
        viewModelScope.launch {

            state.value = state.value.copy(removeFromCartApiState = Resource.Loading)
            val result = cartRepository.removeFromCart(productId)
            if (result.isSuccess) {
                val cart = result.getOrNull()
                state.value = state.value.copy(
                    removeFromCartApiState = Resource.Success(cart),
                    latestCart = cart
                )
            } else {
                state.value = state.value.copy(
                    removeFromCartApiState = Resource.Error(result.exceptionOrNull())
                )
            }

        }
    }

    private fun updateQuantity(productId: String?, quantity: Int) {
        viewModelScope.launch {

            state.value = state.value.copy(updateCartApiState = Resource.Loading)
            val result = cartRepository.updateQuantity(productId, quantity)
            if (result.isSuccess) {
                val cart = result.getOrNull()
                state.value = state.value.copy(
                    updateCartApiState = Resource.Success(cart),
                    latestCart = cart
                )
            } else {
                state.value = state.value.copy(
                    updateCartApiState = Resource.Error(result.exceptionOrNull())
                )
            }

        }
    }

    private fun addToCart(productId: String?) {
        viewModelScope.launch {

            state.value = state.value.copy(addToCartApiState = Resource.Loading)
            val result = cartRepository.addToCart(productId)
            if (result.isSuccess) {
                val cart = result.getOrNull()
                state.value = state.value.copy(
                    addToCartApiState = Resource.Success(cart),
                    latestCart = cart
                )
            } else {
                state.value = state.value.copy(
                    addToCartApiState = Resource.Error(result.exceptionOrNull())
                )
            }

        }
    }

    private fun getCart() {

        viewModelScope.launch {
            state.value = state.value.copy(cartApiState = Resource.Loading)
            val result = cartRepository.getCart()
            if (result.isSuccess) {
                val cart = result.getOrNull()
                state.value = state.value.copy(
                    cartApiState = Resource.Success(cart),
                    latestCart = cart
                )
            } else {
                state.value = state.value.copy(
                    cartApiState = Resource.Error(result.exceptionOrNull())
                )
            }

        }

    }


}


