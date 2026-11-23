package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.route.ecommercekmp.feature.LightColorsScheme
import com.route.ecommercekmp.feature.commerce.domain.model.Category
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartEvents
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.categories_tab.CategoriesView
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.HomeTabView
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.profile.ProfileTabView
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list.WishListTabView
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.ic_categories
import ecommercekmp.shared.generated.resources.ic_home
import ecommercekmp.shared.generated.resources.ic_profile
import ecommercekmp.shared.generated.resources.ic_wishlist
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject


data class NavItemData(val icon: DrawableResource, val index: Int)

@Composable
fun MainBottomNavView(navController: NavController) {
    val cartViewModel = koinInject<CartViewModel>()

    LaunchedEffect(Unit){
        cartViewModel.doAction(CartEvents.GetCart)
    }

    var selectedIndex by remember { mutableIntStateOf(0) }
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    val navItems = listOf(
        NavItemData(Res.drawable.ic_home, 0),
        NavItemData(Res.drawable.ic_categories, 1),
        NavItemData(Res.drawable.ic_wishlist, 2),
        NavItemData(Res.drawable.ic_profile, 3),
    )
    val colorScheme = LightColorsScheme
    val navBarItemColors = NavigationBarItemColors(
        selectedIconColor = colorScheme.primary,
        unselectedIconColor = colorScheme.onPrimary,
        selectedTextColor = colorScheme.onPrimary,
        selectedIndicatorColor = Color.Transparent, // Hide default oval indicator
        unselectedTextColor = colorScheme.onPrimary,
        disabledIconColor = colorScheme.onPrimary,
        disabledTextColor = colorScheme.onPrimary,
    )
    Scaffold(bottomBar = {
        NavigationBar(
            containerColor = colorScheme.primary,
            modifier = Modifier.fillMaxWidth().background(
                color = colorScheme.onSurface, shape = RoundedCornerShape(10.dp)
            )

        ) {
            for (item in navItems) {
                val isSelected = selectedIndex == item.index
                NavigationBarItem(
                    isSelected, onClick = {
                        selectedCategory = null
                        selectedIndex = item.index
                    },

                    icon = {
                        Box(
                            modifier = Modifier.size(48.dp) // Set a fixed size for the circular indicator
                                .background(
                                    color = if (isSelected) colorScheme.onPrimary else Color.Transparent,
                                    shape = CircleShape
                                ), contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }, colors = navBarItemColors
                )
            }
        }
    }, containerColor = Color.White) { innerPadding ->
        Box(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())) {
            when (selectedIndex) {
                0 -> HomeTabView(navController) {
                    selectedIndex = 1
                    selectedCategory = it
                }

                1 -> CategoriesView(navController, selectedCategory)
                2 -> WishListTabView(navController)
                3 -> ProfileTabView(navController)
            }
        }
    }
}




