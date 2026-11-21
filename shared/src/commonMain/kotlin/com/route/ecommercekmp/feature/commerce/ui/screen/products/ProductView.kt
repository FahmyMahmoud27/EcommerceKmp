package com.route.ecommercekmp.feature.commerce.ui.screen.products

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables.ProductCard
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables.SearchTextField
import com.route.ecommercekmp.feature.common.composables.shimmerEffect
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.ic_cart
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductView(navController: NavController, category: String, subCategory: String?) {
    val viewModel = koinViewModel<ProductsViewModel>()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.doAction(ProductsEvents.LoadProducts(category, subCategory.orEmpty()))
    }


    Scaffold() {
        Column(modifier = Modifier.padding(it).fillMaxSize().padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchTextField(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(8.dp))
                Icon(
                    painter = painterResource(Res.drawable.ic_cart),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            when (state.value.productsApiState) {
                is Resource.Loading -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(8) {
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
                                        .height(16.dp).clip(RoundedCornerShape(4.dp))
                                        .shimmerEffect()
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                        .fillMaxWidth(0.7f)
                                        .height(16.dp).clip(RoundedCornerShape(4.dp))
                                        .shimmerEffect()
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Box(
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                        .fillMaxWidth(0.5f)
                                        .height(16.dp).clip(RoundedCornerShape(4.dp))
                                        .shimmerEffect()
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Box(
                                    modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth()
                                        .height(24.dp).clip(RoundedCornerShape(4.dp))
                                        .shimmerEffect()
                                )
                            }
                        }
                    }
                }

                is Resource.Success -> {
                    val products = (state.value.productsApiState as Resource.Success).data
                    if (products?.isNotEmpty() == true) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(products) { product ->
                                ProductCard(product)
                            }
                        }
                    } else {
                        Text("No Products found in this category")
                    }
                }

                is Resource.Error -> {
                    Text("Error loading products")
                }

                else -> {}
            }
        }

    }


}