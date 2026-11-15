package com.route.ecommercekmp.feature.auth.data.repositories.auth_repository.data_source.auth_remote_data_source

import com.route.ecommercekmp.feature.network.httpClient
import com.route.ecommercekmp.feature.network.request.LoginRequest
import com.route.ecommercekmp.feature.network.response.AuthResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.isSuccess

class AuthRemoteDataSourceImpl(val httpClient: HttpClient) : AuthRemoteDataSource {

    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthResponse> = try {
        val response = httpClient.post("v1/auth/signin") {
            setBody(LoginRequest(email, password))
        }
        if (response.status.isSuccess()) {
            val authResponse = response.body<AuthResponse>()
            Result.success(authResponse)
        } else {
            val errorMessage = response.body<AuthResponse>().message ?: ""
            Result.failure(Exception(errorMessage))
        }

    } catch (t: Throwable) {
        println("ahmed: t:$t")
        Result.failure(t)
    }
}
