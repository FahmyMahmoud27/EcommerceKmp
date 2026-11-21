package com.route.ecommercekmp.feature.di.commerce

import com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.HomeRepositoryImpl
import com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.data_source.HomeRemoteDataSource
import com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.data_source.HomeRemoteDataSourceImpl
import com.route.ecommercekmp.feature.commerce.domain.repositories.HomeRepository
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetCategoriesUseCase
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetProductsUseCase
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetSubCategoriesUseCase
import com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.HomeTabViewModel
import com.route.ecommercekmp.feature.commerce.ui.screen.products.ProductsViewModel
import com.route.ecommercekmp.feature.network.httpClient
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val commerceModule = module {
    single<HttpClient> {
        httpClient
    }
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

    factoryOf(::GetSubCategoriesUseCase)
    viewModelOf(::ProductsViewModel)
    singleOf(::HomeTabViewModel)



}
