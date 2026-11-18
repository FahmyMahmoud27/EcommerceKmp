package com.route.ecommercekmp.feature.network.response

import kotlinx.serialization.Serializable


@Serializable
data class Metadata(
    val numberOfPages: Int? = null,
    val limit: Int? = null,
    val currentPage: Int? = null
)
