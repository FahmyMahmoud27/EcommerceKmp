package com.route.ecommercekmp.feature.auth.ui.screens.login

import com.route.ecommercekmp.feature.auth.ui.utils.Resource

data class LoginStates(
    var email: String = "",
    var password: String = "",
    var isPasswordVisible: Boolean = false,
    var loginStates: Resource<Unit>? = null
)

sealed class LoginEvents {
    data class EmailChangedEvent(val email: String) : LoginEvents()
    data class PasswordChangedEvent(val password: String): LoginEvents()
    data class PasswordVisibilityEvent(val isVisible: Boolean) : LoginEvents()
    object LoginClickEvent: LoginEvents()
}