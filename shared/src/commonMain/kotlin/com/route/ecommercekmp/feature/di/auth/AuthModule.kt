package com.route.ecommercekmp.feature.di.auth

import com.route.ecommercekmp.feature.auth.data.repositories.auth_repository.AuthRepositoryImpl
import com.route.ecommercekmp.feature.auth.data.repositories.auth_repository.data_source.auth_remote_data_source.AuthRemoteDataSource
import com.route.ecommercekmp.feature.auth.data.repositories.auth_repository.data_source.auth_remote_data_source.AuthRemoteDataSourceImpl
import com.route.ecommercekmp.feature.auth.domain.repositories.AuthRepository
import com.route.ecommercekmp.feature.auth.domain.usecase.LoginUseCase
import com.route.ecommercekmp.feature.auth.ui.screens.login.LoginViewModel
import com.route.ecommercekmp.feature.network.createHttpClient
import io.ktor.client.HttpClient
import org.koin.dsl.module


val authModule = module {
    single<HttpClient> {
        createHttpClient(get())
    }
    single<AuthRemoteDataSource> {
        AuthRemoteDataSourceImpl(get())
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get(),get())
    }
    single {
        LoginUseCase(get())
    }
    single { LoginViewModel(get()) }
}
