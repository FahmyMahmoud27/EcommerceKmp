package com.route.ecommercekmp.feature.network.request

import kotlinx.serialization.Serializable


@Serializable
data class UpdateCartRequest(val count: Int)
