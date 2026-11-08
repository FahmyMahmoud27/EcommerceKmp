package com.route.ecommercekmp.feature.auth.domain.usecase

import com.route.ecommercekmp.feature.auth.domain.repositories.AuthRepository

class  LoginUseCase(private var authRepo: AuthRepository) {
    suspend fun call(email: String, password: String) : Result<Unit> = authRepo.login(email,password)
}



//47
