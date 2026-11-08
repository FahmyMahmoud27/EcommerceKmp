package com.route.ecommercekmp.feature.auth.ui.utils

sealed class Resource<out T> {
    data object Idle : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T? = null) : Resource<T>()
    data class Error(val exception: Throwable? = null,val massage: String? = exception?.message) : Resource<Nothing>()
}