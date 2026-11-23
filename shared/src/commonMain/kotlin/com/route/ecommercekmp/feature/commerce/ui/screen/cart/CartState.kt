package com.route.ecommercekmp.feature.commerce.ui.screen.cart

import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.model.Cart


data class CartState(
    val cartApiState: Resource<Cart> = Resource.Idle,
    val addToCartApiState: Resource<Cart> = Resource.Idle,
    val removeFromCartApiState: Resource<Cart> = Resource.Idle,
    val updateCartApiState: Resource<Cart> = Resource.Idle,
    val latestCart: Cart? = null
)


sealed class CartEvents {
    object GetCart : CartEvents()
    data class AddToCart(val productId: String?) : CartEvents()
    data class RemoveFromCart(val productId: String?) : CartEvents()
    data class UpdateQuantity(val productId: String?, val quantity: Int) : CartEvents()
}





