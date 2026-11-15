package com.route.ecommercekmp.feature.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient = HttpClient {
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
    defaultRequest {//24
        url("https://ecommerce.routemisr.com/api/")
        contentType(ContentType.Application.Json)
    }

}