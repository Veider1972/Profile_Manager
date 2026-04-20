package ru.veider.profilemanager

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.veider.profilemanager.di.appModule
import ru.veider.profilemanager.di.gsonModule

class MainApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(appModule, gsonModule)
        }
    }
    init{
        app = this
    }
    companion object{
        lateinit var app: MainApp
    }
}

val app get()= MainApp.app