package com.route.ecommercekmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.route.ecommercekmp.feature.di.auth.authModule
import com.route.ecommercekmp.feature.di.commerce.commerceModule
import com.route.ecommercekmp.feature.di.common.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidContext(applicationContext)
                modules(authModule, commonModule, commerceModule)
            }
        }
        setContent {
            App()
        }
    }
}