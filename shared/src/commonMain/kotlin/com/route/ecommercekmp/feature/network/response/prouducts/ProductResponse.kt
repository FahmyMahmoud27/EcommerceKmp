package com.route.ecommercekmp.feature.network.response.prouducts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    @SerialName("data")
    val product: RemoteProduct? = null
)
