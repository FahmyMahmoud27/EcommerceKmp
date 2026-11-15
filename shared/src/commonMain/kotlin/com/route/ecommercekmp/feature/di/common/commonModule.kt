package com.route.ecommercekmp.feature.di.common


import androidx.datastore.preferences.core.stringPreferencesKey
import org.koin.core.module.Module

object DataStoreKeys {
    val USER_TOKEN = stringPreferencesKey("user_token")
}
internal const val dataStoreFileName = "dice.preferences_pb"
expect val commonModule: Module