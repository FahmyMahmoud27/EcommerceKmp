package com.route.ecommercekmp.feature.commerce.domain.model

import kotlinx.serialization.Serializable


////this class act as category and subCategory
@Serializable
data class Category(
    val image: String? = null,
    val name: String = "",
    val category: String? = null,
    val id: String? ="",
)


