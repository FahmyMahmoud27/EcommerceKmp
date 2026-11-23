package com.route.ecommercekmp.feature.commerce.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavController
import com.route.ecommercekmp.AppRoutes
import com.route.ecommercekmp.feature.Blue
import com.route.ecommercekmp.feature.di.common.DataStoreKeys
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.splash_image
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import kotlin.time.Duration.Companion.milliseconds


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SplashView(navController: NavController) {


    var dataStore = koinInject<DataStore<Preferences>>()
    val userTokenFlow: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[DataStoreKeys.USER_TOKEN]
        }

    var token = ""
    LaunchedEffect(Unit) {
        delay(2000.milliseconds)
        userTokenFlow.collectLatest {
            token = it ?: ""
            if (token.isNotEmpty()) {
                navController.navigate(AppRoutes.MainBottomNavRoute)
            } else {
                navController.navigate(AppRoutes.LoginRoute)
            }
        }
    }


    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.splash_image),
                contentDescription = null
            )

            LinearProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 64.dp)
                    .width(160.dp),
                color = Blue,
                trackColor = Blue.copy(alpha = 0.2f)
            )

        }
    }

}


