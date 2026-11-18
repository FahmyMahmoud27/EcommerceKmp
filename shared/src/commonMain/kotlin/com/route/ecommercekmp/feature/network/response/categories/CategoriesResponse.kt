package com.route.ecommercekmp.feature.network.response.categories

import com.route.ecommercekmp.feature.network.response.Metadata
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponse(

    val metadata: Metadata? = null,

    @SerialName("data")
    val categories: List<RemoteCategory>? = null,
    val results: Int? = null
)

