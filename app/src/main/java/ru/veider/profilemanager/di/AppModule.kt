package ru.veider.profilemanager.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.repo.PreferenceImpl
import ru.veider.profilemanager.ui.preference_activity.state.PhoneCapabilities

val appModule = module {

    singleOf(::PhoneCapabilities)

    singleOf(::Gson)
    single<Preference>{ PreferenceImpl( androidContext(), get()) }

}