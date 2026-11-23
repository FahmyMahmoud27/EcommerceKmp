package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list

import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.model.Product

data class WishlistState(
    val wishlistApi: Resource<List<Product>> = Resource.Idle,
    val addToWishlistApi: Resource<List<String>> = Resource.Idle,
    val removeFromWishlistApi: Resource<List<String>> = Resource.Idle
)

@Suppress("UNCHECKED_CAST")
val WishlistState.products: List<Product>
    get() = (wishlistApi as? Resource.Success<*>)?.data as? List<Product> ?: emptyList()

val WishlistState.productIds: Set<String>
    get() = products.mapNotNull { it.id }.toSet()

sealed class WishlistEvents {
    data object GetWishlist : WishlistEvents()
    data class AddToWishlist(val productId: String?) : WishlistEvents()
    data class RemoveFromWishlist(val productId: String?) : WishlistEvents()
}
