package com.route.ecommercekmp.feature.di.commerce

import com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.HomeRepositoryImpl
import com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.data_source.HomeRemoteDataSource
import com.route.ecommercekmp.feature.commerce.data.repositories.home_repository.data_source.HomeRemoteDataSourceImpl
import com.route.ecommercekmp.feature.commerce.domain.repositories.HomeRepository
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetCategoriesUseCase
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetProductsUseCase
import com.route.ecommercekmp.feature.commerce.domain.usecase.GetSubCategoriesUseCase
import com.route.ecommercekmp.feature.network.httpClient
import io.ktor.client.HttpClient
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
    single {
        GetCategoriesUseCase(get())
    }
    single {
        GetProductsUseCase(get())
    }
    single {
        GetSubCategoriesUseCase(get())
    }
}
