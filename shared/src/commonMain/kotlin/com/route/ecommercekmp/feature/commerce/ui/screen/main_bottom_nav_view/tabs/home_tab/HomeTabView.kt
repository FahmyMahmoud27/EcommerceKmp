package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.route.ecommercekmp.feature.commerce.domain.model.Category
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables.AdsCarosuel
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables.CategoriesGridView
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables.ProductsGridView
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables.SearchTextField
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.ic_cart
import ecommercekmp.shared.generated.resources.logo_route_small_blue
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabView(navController: NavController, onCategoryClick: (Category) -> Unit) {
    // Carousel Slider
    val pagerState = rememberPagerState(pageCount = { 3 })

    val viewModel = koinViewModel<HomeTabViewModel>()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.doAction(HomeTabEvents.LoadData)
    }

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Image(
                    painter = painterResource(Res.drawable.logo_route_small_blue),
                    modifier = Modifier.size(50.dp),
                    contentDescription = null
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
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
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                AdsCarosuel(pagerState)
            }

            item {
                CategoriesGridView(state.value.categoryApi, onCategoryClick = onCategoryClick)
            }

            item {
                ProductsGridView(state.value.productApi)
            }







        }

    }
}



