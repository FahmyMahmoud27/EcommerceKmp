 package com.route.ecommercekmp.feature.network.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val message: String? = null,
    val user: User? = null,
    val token: String? = null
)

@Serializable//8
data class User(
    val name: String? = null,
    val email: String? = null,
    val role: String? = null
)
