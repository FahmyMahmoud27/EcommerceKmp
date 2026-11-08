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
        state.value = state.value.copy(email = email)//1:39
    }

    private fun passwordChanged(password: String) {
        state.value = state.value.copy(password = password)
    }

    private fun login() {
        viewModelScope.launch {
            state.value.loginStates = Resource.Loading
            val result = loginUseCase.call(state.value.email, state.value.password)
            if (result.isSuccess) {
                state.value.loginStates = Resource.Success()
            } else {
                state.value.loginStates =
                    Resource.Error(result.exceptionOrNull(), result.exceptionOrNull()?.message)
            }

        }//1.5
    }


}