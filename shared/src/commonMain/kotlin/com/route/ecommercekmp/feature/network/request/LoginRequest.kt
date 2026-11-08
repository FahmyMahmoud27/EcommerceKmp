package com.route.ecommercekmp.feature.network.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(

    val email: String? = null,
    val password: String? = null
)
