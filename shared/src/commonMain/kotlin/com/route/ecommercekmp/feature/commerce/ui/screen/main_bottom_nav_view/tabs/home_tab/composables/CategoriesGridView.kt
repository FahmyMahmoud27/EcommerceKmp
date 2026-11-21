package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.route.ecommercekmp.feature.Blue
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.model.Category
import com.route.ecommercekmp.feature.common.composables.shimmerEffect
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.logo_route_small_blue
import org.jetbrains.compose.resources.painterResource

@Composable
fun CategoriesGridView(
    categoryApiState: Resource<List<Category>>,
    onCategoryClick: (Category) -> Unit
) {
    when (categoryApiState) {
        Resource.Loading -> {
            Box(modifier = Modifier.height(240.dp)) {
                LazyHorizontalGrid(rows = GridCells.Fixed(2), userScrollEnabled = false) {
                    items(10) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(8.dp)
                                    .clip(CircleShape)
                                    .shimmerEffect()
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .height(14.dp)
                                    .width(60.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .shimmerEffect()
                            )
                        }
                    }
                }
            }
        }

        is Resource.Success<List<Category>> -> {
            val categories = categoryApiState.data ?: emptyList()
            Column(modifier = Modifier.height(260.dp)) {
                Text("Categories", style = TextStyle(color = Blue, fontSize = 16.sp))
                Spacer(modifier = Modifier.height(8.dp))
                LazyHorizontalGrid(rows = GridCells.Fixed(2)) {
                    items(categories.size) {
                        val category = categories[it]
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable {
                                onCategoryClick(category)
                            }
                        ) {
                            AsyncImage(
                                model = category.image ?: "",
                                modifier = Modifier.size(100.dp)
                                    .padding(8.dp)
                                    .clip(CircleShape),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                error = painterResource(Res.drawable.logo_route_small_blue)
                            )
                            Text(
                                category.name, style = TextStyle(
                                    color = Blue,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                }
            }

        }

        else -> {}
    }
}