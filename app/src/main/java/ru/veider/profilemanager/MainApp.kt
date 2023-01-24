package ru.veider.profilemanager

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.veider.profilemanager.di.appModule
import ru.veider.profilemanager.di.repoModule

class MainApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(repoModule, appModule)
        }
    }
}