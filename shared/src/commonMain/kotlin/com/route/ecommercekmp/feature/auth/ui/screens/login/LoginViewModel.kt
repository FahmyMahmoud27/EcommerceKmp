package com.route.ecommercekmp.feature.auth.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.ecommercekmp.feature.auth.domain.usecase.LoginUseCase
import com.route.ecommercekmp.feature.auth.ui.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(var loginUseCase: LoginUseCase) : ViewModel() {

    var state: MutableStateFlow<LoginStates> = MutableStateFlow(LoginStates())

    fun doAction(event: LoginEvents) {
        when (event) {
            is LoginEvents.EmailChangedEvent -> {
                emailChanged(event.email)
            }

            is LoginEvents.PasswordChangedEvent -> {
                passwordChanged(event.password)
            }

            is LoginEvents.PasswordVisibilityEvent -> {
                state.value.isPasswordVisible = event.isVisible
            }

            is LoginEvents.LoginClickEvent -> {
                login()
            }
        }
    }

    private fun emailChanged(email: String) {
        println("emailChanged: $email${state.value.email}")
        state.value = state.value.copy(email = email)//1:39
        println("after change state ${state.value.email}")
    }

    private fun passwordChanged(password: String) {
        state.value = state.value.copy(password = password)
    }

    private fun login() {
        println("login function called")
        viewModelScope.launch {
            println("in scope")
            state.value = state.value.copy(loginStates  = Resource.Loading)
            val result = loginUseCase.call(state.value.email, state.value.password)
            if (result.isSuccess) {
                state.value = state.value.copy(loginStates = Resource.Success())
            } else {
                state.value = state.value.copy(
                    loginStates = Resource.Error(
                        result.exceptionOrNull(),
                        result.exceptionOrNull()?.message
                    )
                )
            }
        }
    }


}