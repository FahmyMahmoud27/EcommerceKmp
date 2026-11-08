package com.route.ecommercekmp.feature.auth.data.repositories.auth_repository.data_source.auth_remote_data_source

import com.route.ecommercekmp.feature.network.response.AuthResponse

interface AuthRemoteDataSource {
    suspend fun login(email: String, password: String): Result<AuthResponse>
}