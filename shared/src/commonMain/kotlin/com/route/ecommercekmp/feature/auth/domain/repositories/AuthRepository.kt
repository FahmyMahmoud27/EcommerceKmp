package com.route.ecommercekmp.feature.auth.domain.repositories


interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
}