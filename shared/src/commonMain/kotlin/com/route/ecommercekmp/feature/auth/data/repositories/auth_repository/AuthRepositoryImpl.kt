package com.route.ecommercekmp.feature.auth.data.repositories.auth_repository

import com.route.ecommercekmp.feature.auth.data.repositories.auth_repository.data_source.auth_remote_data_source.AuthRemoteDataSource
import com.route.ecommercekmp.feature.auth.domain.repositories.AuthRepository
import com.route.ecommercekmp.feature.network.response.AuthResponse

class AuthRepositoryImpl(var authRemoteDataSource: AuthRemoteDataSource) : AuthRepository {
    override suspend fun login(
        email: String,//41 mapper
        password: String
    ): Result<Unit> {
        val result = authRemoteDataSource.login(email, password)
        return if (result.isSuccess) {
            Result.success(Unit)
        } else {
            Result.failure(
                result.exceptionOrNull() ?: Exception("Something went wrong please try again later")
            )
        }
    }
}

