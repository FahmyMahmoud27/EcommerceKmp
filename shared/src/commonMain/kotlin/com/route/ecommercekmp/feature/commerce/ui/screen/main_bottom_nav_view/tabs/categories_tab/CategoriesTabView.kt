package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.categories_tab


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.route.ecommercekmp.AppRoutes
import com.route.ecommercekmp.feature.Blue
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import com.route.ecommercekmp.feature.commerce.domain.model.Category
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.categories_tab.composables.SubcategoriesGridView
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.HomeTabEvents
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.HomeTabViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables.SearchTextField
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.ic_cart
import ecommercekmp.shared.generated.resources.logo_route_small_blue
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Composable
fun CategoriesView(navController: NavController, selectedCategory: Category? = null) {
    val viewModel = koinInject<HomeTabViewModel>()
    val state = viewModel.state.collectAsState()
    var selectedCategory by remember {
        if (state.value.categoryApi is Resource.Success) {
            val categories = (state.value.categoryApi as Resource.Success<List<Category>>).data
            val index = if (selectedCategory == null) 0 else categories?.indexOf(selectedCategory)
            mutableStateOf(
                categories?.get(index ?: 0)
            )
        } else mutableStateOf(
            null
        )
    }

    LaunchedEffect(selectedCategory) {
        selectedCategory?.let {
            viewModel.doAction(HomeTabEvents.LoadSubCategories(it))
        }
    }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize().padding(16.dp)) {
            Image(
                painter = painterResource(Res.drawable.logo_route_small_blue),
                modifier = Modifier.size(40.dp),
                contentDescription = null
            )
            Row(
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

            if (state.value.categoryApi is Resource.Success) {
                val categories = (state.value.categoryApi as Resource.Success<List<Category>>).data
                    ?: emptyList()
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxHeight()
                            .weight(1f)
                            .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                            .background(Color(0xFFF3F5F7))
                    ) {
                        items(categories) { category ->
                            val isSelected = category == selectedCategory
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(72.dp)
                                    .background(if (isSelected) Color.White else Color.Transparent)
                                    .clickable {
                                        selectedCategory = category
                                    }
                            ) {
                                // Indicator
                                Box(
                                    modifier = Modifier
                                        .height(72.dp)
                                        .width(4.dp)
                                        .background(
                                            if (isSelected) Blue else Color.Transparent,
                                            shape = RoundedCornerShape(
                                                topEnd = 4.dp,
                                                bottomEnd = 4.dp
                                            )
                                        )
                                )
                                Text(
                                    text = category.name,
                                    style = TextStyle(
                                        color = Blue,
                                        fontSize = 14.sp,
                                        fontWeight = if (isSelected) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Medium
                                    ),
                                    modifier = Modifier.padding(start = 12.dp, end = 8.dp)
                                )
                            }
                        }
                    }

                    SubcategoriesGridView(
                        subcategoriesApiState = state.value.subCategoriesApi,
                        modifier = Modifier.weight(2f)
                    ) {
                        navController.navigate(
                            AppRoutes.ProductsRoute(
                                selectedCategory?.id ?: "",
                                it.id
                            )
                        )
                    }
                }
            } else {
                CircularProgressIndicator()
            }
        }

    }
}