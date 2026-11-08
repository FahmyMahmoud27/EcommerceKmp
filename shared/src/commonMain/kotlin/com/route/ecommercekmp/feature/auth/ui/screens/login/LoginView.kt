package com.route.ecommercekmp.feature.auth.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.ecommercekmp.feature.auth.ui.components.CustomButton
import com.route.ecommercekmp.feature.auth.ui.components.CustomTextField
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.logo_route
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginView() {
    val viewModel = koinViewModel<LoginViewModel>()
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF004182))
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Image(
            painter = painterResource(Res.drawable.logo_route),
            contentDescription = "Route Logo",
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Welcome Back To Route",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Please sign in with your mail",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        CustomTextField(
            value = state.email,
            onValueChange = {
                viewModel.doAction(LoginEvents.EmailChangedEvent(it))
            },
            label = "User Name",
            placeholder = "enter your name"
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            value = state.password,
            onValueChange = {
                viewModel.doAction(LoginEvents.PasswordChangedEvent(it))
            },
            label = "Password",
            placeholder = "enter your password",
            visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                Text(
                    text = if (state.isPasswordVisible) "👁" else "🙈",
                    modifier = Modifier.clickable {
                        viewModel.doAction(LoginEvents.PasswordVisibilityEvent(!state.isPasswordVisible))
                    }
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Forgot password",
            color = Color.White,
            modifier = Modifier.align(Alignment.End).clickable { /* Handle forgot password */ }
        )

        Spacer(modifier = Modifier.height(56.dp))

        CustomButton(
            text = "Login",
            onClick = {
                viewModel.doAction(LoginEvents.LoginClickEvent)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.clickable {

            },
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account? ",
                color = Color.White
            )
            Text(
                text = "Create Account",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}