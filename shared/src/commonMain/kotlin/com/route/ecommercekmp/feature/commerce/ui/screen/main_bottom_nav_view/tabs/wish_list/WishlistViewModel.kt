package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.repositories.WishlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val wishlistRepository: WishlistRepository
) : ViewModel() {

    val state: MutableStateFlow<WishlistState> = MutableStateFlow(WishlistState())

    fun doAction(event: WishlistEvents) {
        when (event) {
            is WishlistEvents.GetWishlist -> getWishlist()
            is WishlistEvents.AddToWishlist -> addToWishlist(event.productId)
            is WishlistEvents.RemoveFromWishlist -> removeFromWishlist(event.productId)
        }
    }

    private fun getWishlist(showLoading: Boolean = true) {
        viewModelScope.launch {
            if (showLoading) {
                state.value = state.value.copy(wishlistApi = Resource.Loading)
            }
            val result = wishlistRepository.getWishlist()
            val products = result.getOrNull()
            if (result.isSuccess && products != null) {
                state.value = state.value.copy(wishlistApi = Resource.Success(products))
            } else {
                state.value = state.value.copy(
                    wishlistApi = Resource.Error(result.exceptionOrNull())
                )
            }
        }
    }

    private fun addToWishlist(productId: String?) {
        if (productId.isNullOrEmpty()) return
        viewModelScope.launch {
            state.value = state.value.copy(addToWishlistApi = Resource.Loading)
            val result = wishlistRepository.addToWishlist(productId)
            if (result.isSuccess) {
                state.value = state.value.copy(
                    addToWishlistApi = Resource.Success(result.getOrNull())
                )
                getWishlist(showLoading = false)
            } else {
                state.value = state.value.copy(
                    addToWishlistApi = Resource.Error(result.exceptionOrNull())
                )
            }
        }
    }

    private fun removeFromWishlist(productId: String?) {
        if (productId.isNullOrEmpty()) return
        viewModelScope.launch {
            state.value = state.value.copy(
                removeFromWishlistApi = Resource.Loading,
                wishlistApi = Resource.Success(
                    state.value.products.filterNot { it.id == productId }
                )
            )
            val result = wishlistRepository.removeFromWishlist(productId)
            if (result.isSuccess) {
                state.value = state.value.copy(
                    removeFromWishlistApi = Resource.Success(result.getOrNull())
                )
                getWishlist(showLoading = false)
            } else {
                state.value = state.value.copy(
                    removeFromWishlistApi = Resource.Error(result.exceptionOrNull())
                )
                getWishlist(showLoading = false)
            }
        }
    }
}
