package com.example.testtask

import android.app.Application
import com.example.testtask.di.appModule
import com.exemple.testtask.data.di.dataModule
import com.exemple.testtask.feature.di.mainScreenModule
import com.exemple.testtask.feature.registration.di.registrationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(
                appModule,
                registrationModule,
                dataModule,
                mainScreenModule,

            )
        }
    }
}