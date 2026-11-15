package com.route.ecommercekmp.feature.di.common

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioStorage
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesSerializer
import okio.FileSystem
import okio.Path.Companion.toPath
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val commonModule: Module = module {
    single<DataStore<Preferences>> {
        DataStoreFactory.create(
            storage = OkioStorage(
                fileSystem = FileSystem.SYSTEM,
                serializer = PreferencesSerializer,
                producePath = { androidContext().filesDir.resolve(dataStoreFileName).absolutePath.toPath() }
            )
        )
    }
}

