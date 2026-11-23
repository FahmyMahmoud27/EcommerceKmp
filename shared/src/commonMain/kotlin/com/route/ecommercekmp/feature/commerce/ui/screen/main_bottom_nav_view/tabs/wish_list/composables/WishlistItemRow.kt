package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list.composables

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
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.composables.QuantityControl
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.ic_wishlist
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun WishlistItemRow(
    product: Product,
    quantityInCart: Int?,
    onAddToCart: () -> Unit,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Blue.copy(alpha = 0.25f), RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = product.imageCover,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp).clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = product.title,
                    style = TextStyle(
                        color = Blue,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Blue)
                        .clickable { onRemove() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_wishlist),
                        contentDescription = "Remove from wishlist",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            if (product.brandName.isNotBlank()) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = product.brandName,
                    style = TextStyle(color = Blue.copy(alpha = 0.6f), fontSize = 12.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "EGP ${product.priceAfterDiscount ?: product.price}",
                        style = TextStyle(
                            color = Blue,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    if (product.priceAfterDiscount != null) {
                        Text(
                            text = "EGP ${product.price}",
                            style = TextStyle(
                                color = Blue.copy(alpha = 0.6f),
                                fontSize = 11.sp,
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                    }
                }

                if (quantityInCart != null) {
                    QuantityControl(
                        quantity = quantityInCart,
                        onDecrease = onDecrease,
                        onIncrease = onIncrease
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Blue)
                            .clickable { onAddToCart() }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Add to Cart",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}
