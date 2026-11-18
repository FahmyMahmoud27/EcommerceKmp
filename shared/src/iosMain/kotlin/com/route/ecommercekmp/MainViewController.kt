package com.route.ecommercekmp

import androidx.compose.ui.window.ComposeUIViewController
import com.route.ecommercekmp.feature.di.auth.authModule
import com.route.ecommercekmp.feature.di.commerce.commerceModule
import com.route.ecommercekmp.feature.di.common.commonModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    startKoin {
        modules(authModule, commonModule, commerceModule)
    }
    App()

}
