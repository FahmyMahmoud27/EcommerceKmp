package com.route.ecommercekmp.feature.commerce.ui.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.route.ecommercekmp.feature.Blue
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.composables.CartContent
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.composables.CartHeader
import org.koin.compose.koinInject

@Composable
fun CartView(navController: NavController) {
    val viewModel = koinInject<CartViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.doAction(CartEvents.GetCart)
    }

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        CartHeader(onBack = { navController.navigateUp() })

        when (val resource = state.cartApiState) {
            Resource.Loading -> Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Blue)
            }

            is Resource.Error -> Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("Error loading cart", style = TextStyle(color = Blue, fontSize = 14.sp))
            }

            else -> {
                val cart = state.latestCart
                if (cart == null || cart.products.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Your cart is empty",
                            style = TextStyle(color = Blue.copy(alpha = 0.6f), fontSize = 25.sp)
                        )
                    }
                } else {
                    CartContent(
                        cart = cart,
                        viewModel = viewModel,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
