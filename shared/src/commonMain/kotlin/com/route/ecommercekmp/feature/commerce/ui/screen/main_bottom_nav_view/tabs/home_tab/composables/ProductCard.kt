package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.route.ecommercekmp.feature.Blue
import com.route.ecommercekmp.feature.commerce.domain.model.Product
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartEvents
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list.WishlistEvents
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list.WishlistViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list.productIds
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.ic_plus
import ecommercekmp.shared.generated.resources.ic_star
import ecommercekmp.shared.generated.resources.ic_wishlist
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onProductClick: (Product) -> Unit = {}
) {
    val cartViewModel = koinViewModel<CartViewModel>()
    val cartState = cartViewModel.state.collectAsState()

    val wishlistViewModel = koinInject<WishlistViewModel>()
    val wishlistState by wishlistViewModel.state.collectAsState()

    var cartProduct = cartState.value.latestCart?.products[product.id]
    val isInCart = cartProduct != null
    val isFavorite = product.id?.let { wishlistState.productIds.contains(it) } == true


    Column(
        modifier = modifier
            .width(180.dp)
            .border(
                width = 1.dp,
                color = Blue.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { onProductClick(product) }
    ) {
        // Image Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        ) {
            AsyncImage(
                model = product.imageCover,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(130.dp)
            )
            // Heart Icon
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
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
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Details Section
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = product.title,
                style = TextStyle(
                    color = Blue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = product.description,
                style = TextStyle(
                    color = Blue,
                    fontSize = 14.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "EGP ${product.price}",
                    style = TextStyle(
                        color = Blue,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                if (product.priceAfterDiscount != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${product.priceAfterDiscount} EGP",
                        style = TextStyle(
                            color = Blue.copy(alpha = 0.6f),
                            fontSize = 12.sp,
                            textDecoration = TextDecoration.LineThrough
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Review (${product.ratingsAverage ?: 0.0})",
                    style = TextStyle(
                        color = Blue,
                        fontSize = 12.sp
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(Res.drawable.ic_star),
                    contentDescription = "Rating",
//                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(16.dp),
                )
            }
            if (isInCart) {
                Row {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Blue),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.ic_star),
                            contentDescription = "Add to Cart",
//                        tint = Color.White,
                            modifier = Modifier.size(20.dp)
                                .clickable {
                                    if (cartProduct.cartQuantity == 1) {
                                        cartViewModel.doAction(CartEvents.RemoveFromCart(product.id))
                                    } else {
                                        cartViewModel.doAction(
                                            CartEvents.UpdateQuantity(
                                                product.id,
                                                cartProduct.cartQuantity - 1

                                            )
                                        )
                                    }
                                }
                        )
                    }

                    Text(cartProduct.cartQuantity.toString())

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Blue),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.ic_plus),
                            contentDescription = "Add to Cart",
//                        tint = Color.White,
                            modifier = Modifier.size(20.dp).clickable{
                                cartViewModel.doAction(
                                    CartEvents.UpdateQuantity(
                                        product.id,
                                        cartProduct.cartQuantity + 1

                                    )
                                )
                            }
                        )
                    }
                }

            } else {
                Spacer(modifier = Modifier.weight(1f))

                // Add Button
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Blue)
                        .clickable {
                            cartViewModel.doAction(CartEvents.AddToCart(product.id))
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_plus),
                        contentDescription = "Add to Cart",
//                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }


            }
        }
    }
}