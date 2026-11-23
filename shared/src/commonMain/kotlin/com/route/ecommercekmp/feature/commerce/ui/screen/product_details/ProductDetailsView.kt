package com.route.ecommercekmp.feature.commerce.ui.screen.product_details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.route.ecommercekmp.feature.Blue
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.model.Product
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartEvents
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartState
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list.WishlistEvents
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list.WishlistViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list.productIds
import com.route.ecommercekmp.feature.common.composables.shimmerEffect
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.back_arrow
import ecommercekmp.shared.generated.resources.ic_plus
import ecommercekmp.shared.generated.resources.ic_star
import ecommercekmp.shared.generated.resources.ic_wishlist
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetailsView(navController: NavController, productId: String) {
    val viewModel = koinViewModel<ProductDetailsViewModel>()
    val cartViewModel = koinViewModel<CartViewModel>()
    val state by viewModel.state.collectAsState()
    val cartState by cartViewModel.state.collectAsState()

    LaunchedEffect(productId) {
        viewModel.doAction(ProductDetailsEvents.LoadProduct(productId))
    }
    LaunchedEffect(Unit) {
        if (cartState.latestCart == null) {
            cartViewModel.doAction(CartEvents.GetCart)
        }
    }

    Scaffold { innerPadding ->
        when (val resource = state.productApi) {
            Resource.Loading -> Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Blue)
            }

            is Resource.Error -> Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Error loading product", color = Blue)
            }

            else -> {
                val product = (resource as? Resource.Success<Product>)?.data
                if (product == null) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Product not found", color = Blue)
                    }
                } else {
                    ProductDetailsContent(
                        product = product,
                        cartState = cartState,
                        cartViewModel = cartViewModel,
                        onBack = { navController.navigateUp() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProductDetailsContent(
    product: Product,
    cartState: CartState,
    cartViewModel: CartViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val wishlistViewModel = koinInject<WishlistViewModel>()
    val wishlistState by wishlistViewModel.state.collectAsState()
    val isFavorite = product.id?.let { wishlistState.productIds.contains(it) } == true

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { onBack() },

                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.back_arrow),
                        contentDescription = "Back",
                        tint = Blue,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Product Details",
                    style = TextStyle(color = Blue, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .border(1.dp, Blue.copy(alpha = 0.3f), CircleShape)
                    .background(if (isFavorite) Blue else Color.White)
                    .clickable {
                        if (isFavorite) {
                            wishlistViewModel.doAction(WishlistEvents.RemoveFromWishlist(product.id))
                        } else {
                            wishlistViewModel.doAction(WishlistEvents.AddToWishlist(product.id))
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_wishlist),
                    contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                    tint = if (isFavorite) Color.White else Blue,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val images = product.images.ifEmpty {
            listOfNotNull(product.imageCover.takeIf { it.isNotBlank() })
        }
        if (images.isNotEmpty()) {
            val pagerState = rememberPagerState(pageCount = { images.size })
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth().height(260.dp)
            ) { page ->
                AsyncImage(
                    model = images[page],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(16.dp))
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF3F5F7))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = product.title,
            style = TextStyle(color = Blue, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
        if (product.brandName.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Brand: ${product.brandName}",
                style = TextStyle(color = Blue, fontSize = 14.sp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(Res.drawable.ic_star),
                contentDescription = "Rating",
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${product.ratingsAverage ?: 0.0} (${product.ratingsQuantity} reviews)",
                style = TextStyle(color = Blue, fontSize = 13.sp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "EGP ${product.priceAfterDiscount ?: product.price}",
                style = TextStyle(color = Blue, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            if (product.priceAfterDiscount != null) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "EGP ${product.price}",
                    style = TextStyle(
                        color = Blue.copy(alpha = 0.6f),
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.LineThrough
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Description",
            style = TextStyle(color = Blue, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = product.description,
            style = TextStyle(color = Blue, fontSize = 14.sp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        val cartProduct = product.id?.let { cartState.latestCart?.products?.get(it) }
        if (cartProduct != null) {
            QuantityControl(
                quantity = cartProduct.cartQuantity,
                onDecrease = {
                    if (cartProduct.cartQuantity == 1) {
                        cartViewModel.doAction(CartEvents.RemoveFromCart(product.id))
                    } else {
                        cartViewModel.doAction(
                            CartEvents.UpdateQuantity(product.id, cartProduct.cartQuantity - 1)
                        )
                    }
                },
                onIncrease = {
                    cartViewModel.doAction(
                        CartEvents.UpdateQuantity(product.id, cartProduct.cartQuantity + 1)
                    )
                }
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Blue)
                    .clickable {
                        cartViewModel.doAction(CartEvents.AddToCart(product.id))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add to cart",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun QuantityControl(
    quantity: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Blue)
                .clickable { onDecrease() },
            contentAlignment = Alignment.Center
        ) {
            Text("−", color = Color.White, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = quantity.toString(),
            style = TextStyle(color = Blue, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Blue)
                .clickable { onIncrease() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_plus),
                contentDescription = "Increase quantity",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
