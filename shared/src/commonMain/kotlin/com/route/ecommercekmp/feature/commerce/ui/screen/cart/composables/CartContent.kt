package com.route.ecommercekmp.feature.commerce.ui.screen.cart.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.route.ecommercekmp.feature.commerce.domain.model.Cart
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartEvents
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartViewModel

@Composable
internal fun CartContent(
    cart: Cart,
    viewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(cart.products.values.toList()) { product ->
                CartItemRow(
                    product = product,
                    onDecrease = {
                        if (product.cartQuantity == 1) {
                            viewModel.doAction(CartEvents.RemoveFromCart(product.id))
                        } else {
                            viewModel.doAction(
                                CartEvents.UpdateQuantity(product.id, product.cartQuantity - 1)
                            )
                        }
                    },
                    onIncrease = {
                        viewModel.doAction(
                            CartEvents.UpdateQuantity(product.id, product.cartQuantity + 1)
                        )
                    },
                    onRemove = {
                        viewModel.doAction(CartEvents.RemoveFromCart(product.id))
                    }
                )
            }
        }

        CartTotalBar(cart = cart)
    }
}
