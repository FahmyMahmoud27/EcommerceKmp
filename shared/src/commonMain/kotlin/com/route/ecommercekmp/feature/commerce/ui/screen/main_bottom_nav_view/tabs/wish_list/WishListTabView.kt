package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.route.ecommercekmp.AppRoutes
import com.route.ecommercekmp.feature.Blue
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartEvents
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables.SearchTextField
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list.composables.WishlistItemRow
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.ic_cart
import ecommercekmp.shared.generated.resources.logo_route_small_blue
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WishListTabView(navController: NavController) {
    val viewModel = koinInject<WishlistViewModel>()
    val cartViewModel = koinViewModel<CartViewModel>()
    val state by viewModel.state.collectAsState()
    val cartState by cartViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.doAction(WishlistEvents.GetWishlist)
        if (cartState.latestCart == null) {
            cartViewModel.doAction(CartEvents.GetCart)
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.logo_route_small_blue),
                modifier = Modifier.size(50.dp),
                contentDescription = null
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchTextField(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(8.dp))
                Icon(
                    painter = painterResource(Res.drawable.ic_cart),
                    contentDescription = "Open cart",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.navigate(AppRoutes.CartRoute) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (val resource = state.wishlistApi) {
                Resource.Idle, Resource.Loading -> Box(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Blue)
                }

                is Resource.Error -> Box(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Couldn't load your wishlist",
                        style = TextStyle(color = Blue, fontSize = 14.sp)
                    )
                }

                is Resource.Success -> {
                    val products = state.products
                    if (products.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Your wishlist is empty",
                                style = TextStyle(color = Blue.copy(alpha = 0.6f), fontSize = 14.sp)
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            contentPadding = PaddingValues(bottom = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(products, key = { it.id ?: it.title }) { product ->
                                val cartProduct = product.id?.let {
                                    cartState.latestCart?.products?.get(it)
                                }
                                WishlistItemRow(
                                    product = product,
                                    quantityInCart = cartProduct?.cartQuantity,
                                    onAddToCart = {
                                        cartViewModel.doAction(CartEvents.AddToCart(product.id))
                                    },
                                    onDecrease = {
                                        if (cartProduct?.cartQuantity == 1) {
                                            cartViewModel.doAction(
                                                CartEvents.RemoveFromCart(product.id)
                                            )
                                        } else {
                                            cartViewModel.doAction(
                                                CartEvents.UpdateQuantity(
                                                    product.id,
                                                    (cartProduct?.cartQuantity ?: 1) - 1
                                                )
                                            )
                                        }
                                    },
                                    onIncrease = {
                                        cartViewModel.doAction(
                                            CartEvents.UpdateQuantity(
                                                product.id,
                                                (cartProduct?.cartQuantity ?: 0) + 1
                                            )
                                        )
                                    },
                                    onRemove = {
                                        viewModel.doAction(
                                            WishlistEvents.RemoveFromWishlist(product.id)
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
