package com.route.ecommercekmp.feature.network

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.route.ecommercekmp.feature.di.common.DataStoreKeys
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json


fun createHttpClient(dataStore: DataStore<Preferences>): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
        defaultRequest {
            url("https://ecommerce.routemisr.com/api/")
            contentType(ContentType.Application.Json)
            val token = runBlocking {
                dataStore.data
                    .map { preferences ->
                        preferences[DataStoreKeys.USER_TOKEN]
                    }.firstOrNull()
            }
            if (!token.isNullOrEmpty()) {
                header("token", token)
            }
        }
    }
}

