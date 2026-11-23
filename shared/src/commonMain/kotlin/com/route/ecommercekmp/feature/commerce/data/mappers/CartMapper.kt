package com.route.ecommercekmp.feature.commerce.data.mappers

import com.route.ecommercekmp.feature.commerce.domain.model.Cart
import com.route.ecommercekmp.feature.commerce.domain.model.Product
import com.route.ecommercekmp.feature.network.response.cart.RemoteCart

fun RemoteCart.toCart(): Cart {

    val entries = mutableMapOf<String, Product>()
    remoteCartItems?.forEach {
        val remoteItem = it ?: return@forEach
        val product = remoteItem.product?.toProduct() ?: return@forEach
        product.cartQuantity = remoteItem.count ?: 0
        val itemPrice = remoteItem.price?.toDouble()
        entries[remoteItem.product.id ?: ""] =
            if (itemPrice != null && itemPrice > 0) product.copy(price = itemPrice) else product
    }
    return Cart(
        products = entries,
        totalPrice = totalCartPrice ?: 0.0
    )

}



