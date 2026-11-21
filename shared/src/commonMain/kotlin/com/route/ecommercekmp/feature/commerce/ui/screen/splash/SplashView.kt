package com.route.ecommercekmp.feature.commerce.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavController
import com.route.ecommercekmp.AppRoutes
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


    Scaffold(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(Res.drawable.splash_image), contentDescription = null)

    }

}


