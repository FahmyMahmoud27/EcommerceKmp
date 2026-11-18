package com.route.ecommercekmp.feature.commerce.data.mappers

import com.route.ecommercekmp.feature.commerce.domain.model.Category
import com.route.ecommercekmp.feature.network.response.categories.RemoteCategory


fun RemoteCategory.toCategory() = Category(
    id = id ?: "",
    image = image,
    name = name ?: "UnKnown",
    category = category
)





