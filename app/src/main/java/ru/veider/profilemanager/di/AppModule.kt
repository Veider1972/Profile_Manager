package ru.veider.profilemanager.di

import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.veider.profilemanager.repo.*
import ru.veider.profilemanager.data.PhoneCapabilities

val appModule = module {

    singleOf(::PhoneCapabilities)

    singleOf(::Gson)
    single<Preference>{ PreferenceImpl( androidContext(), get()) }

}