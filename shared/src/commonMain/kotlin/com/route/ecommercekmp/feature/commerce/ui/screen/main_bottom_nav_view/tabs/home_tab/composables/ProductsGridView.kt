package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.ecommercekmp.feature.Blue
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.model.Product
import com.route.ecommercekmp.feature.common.composables.shimmerEffect

@Composable
fun ProductsGridView(
    productsApiState: Resource<List<Product>>,
    onProductClick: (Product) -> Unit
) {
    when (productsApiState) {
        Resource.Loading -> {
            LazyRow(
                contentPadding = PaddingValues(end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                userScrollEnabled = false
            ) {
                items(5) {
                    Column(
                        modifier = Modifier
                            .width(180.dp)
                            .height(280.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp)
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth()
                                .height(16.dp).clip(RoundedCornerShape(4.dp)).shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth(0.7f)
                                .height(16.dp).clip(RoundedCornerShape(4.dp)).shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth(0.5f)
                                .height(16.dp).clip(RoundedCornerShape(4.dp)).shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth()
                                .height(24.dp).clip(RoundedCornerShape(4.dp)).shimmerEffect()
                        )
                    }
                }
            }
        }

        is Resource.Success<List<Product>> -> {
            Column {
                Text("Products", style = TextStyle(color = Blue, fontSize = 16.sp))
                val products = productsApiState.data ?: emptyList()
                LazyRow(
                    contentPadding = PaddingValues(end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(products.size) {
                        val product = products[it]
                        ProductCard(product = product, onProductClick = onProductClick)
                    }
                }
            }

        }

        else -> {}
    }
}