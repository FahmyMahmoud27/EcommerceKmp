package com.route.ecommercekmp.feature.auth.data.repositories.auth_repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.route.ecommercekmp.feature.auth.data.repositories.auth_repository.data_source.auth_remote_data_source.AuthRemoteDataSource
import com.route.ecommercekmp.feature.auth.domain.repositories.AuthRepository
import com.route.ecommercekmp.feature.di.common.DataStoreKeys

class AuthRepositoryImpl(
    val authRemoteDataSource: AuthRemoteDataSource,
    val dataStore: DataStore<Preferences>
) : AuthRepository {
    override suspend fun login(
        email: String,//41 mapper
        password: String
    ): Result<Unit> {
        val result = authRemoteDataSource.login(email, password)
        return if (result.isSuccess) {
            dataStore.edit { preferences ->
                preferences[DataStoreKeys.USER_TOKEN] = result.getOrNull()?.token ?: ""
            }
            Result.success(Unit)
        } else {
            Result.failure(
                result.exceptionOrNull() ?: Exception("Something went wrong please try again later")
            )
        }
    }
}

