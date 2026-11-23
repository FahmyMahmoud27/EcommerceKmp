package com.route.ecommercekmp.feature.di.commerce

import com.route.ecommercekmp.feature.commerce.data.repositories.cart_repository.CartRepositoryImpl
import com.route.ecommercekmp.feature.commerce.data.repositories.cart_repository.data_source.CartRemoteDataSource
import com.route.ecommercekmp.feature.commerce.data.repositories.cart_repository.data_source.CartRemoteDataSourceImpl
import com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.HomeRepositoryImpl
import com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.data_source.HomeRemoteDataSource
import com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.data_source.HomeRemoteDataSourceImpl
import com.route.ecommercekmp.feature.commerce.data.repositories.wishlist_repository.WishlistRepositoryImpl
import com.route.ecommercekmp.feature.commerce.data.repositories.wishlist_repository.data_source.WishlistRemoteDataSource
import com.route.ecommercekmp.feature.commerce.data.repositories.wishlist_repository.data_source.WishlistRemoteDataSourceImpl
import com.route.ecommercekmp.feature.commerce.domain.repositories.CartRepository
import com.route.ecommercekmp.feature.commerce.domain.repositories.HomeRepository
import com.route.ecommercekmp.feature.commerce.domain.repositories.WishlistRepository
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetCategoriesUseCase
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetProductByIdUseCase
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetProductsUseCase
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetSubCategoriesUseCase
import com.route.ecommercekmp.feature.commerce.ui.screen.cart.CartViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.HomeTabViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.wish_list.WishlistViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.product_details.ProductDetailsViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.products.ProductsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val commerceModule = module {
    single<HomeRemoteDataSource> {
        HomeRemoteDataSourceImpl(get())
    }
    single<HomeRepository> {
        HomeRepositoryImpl(get())
    }
    factory {
        GetCategoriesUseCase(get())
    }
    factory {
        GetProductsUseCase(get())
    }
    factory {
        GetSubCategoriesUseCase(get())
    }
    factory {
        GetProductByIdUseCase(get())
    }

    factoryOf(::GetSubCategoriesUseCase)
    viewModelOf(::ProductsViewModel)
    viewModelOf(::ProductDetailsViewModel)
    singleOf(::HomeTabViewModel)


    factory<CartRemoteDataSource> {
        CartRemoteDataSourceImpl(get())
    }

    factory<CartRepository> {
        CartRepositoryImpl(get())
    }

    singleOf(::CartViewModel)


    factory<WishlistRemoteDataSource> {
        WishlistRemoteDataSourceImpl(get())
    }

    factory<WishlistRepository> {
        WishlistRepositoryImpl(get())
    }

    singleOf(::WishlistViewModel)


}
