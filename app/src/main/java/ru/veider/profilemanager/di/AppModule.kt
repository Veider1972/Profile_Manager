package ru.veider.profilemanager.di

import androidx.room.Room
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.veider.profilemanager.domain.UseCases
import ru.veider.profilemanager.domain.UseCasesImpl
import ru.veider.profilemanager.repo.localrepo.LocalRepo
import ru.veider.profilemanager.repo.localrepo.LocalRepoImpl
import ru.veider.profilemanager.repo.localrepo.ProfileDatabase
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

val appModule = module {

    singleOf(::LocalRepoImpl) {bind<LocalRepo>()}
//    singleOf(::RepoImpl) {bind<Repo>()}
    singleOf(::UseCasesImpl) {bind<UseCases>()}
    singleOf(::PreferenceViewModel)
}

val repoModule = module {
    single {Room.databaseBuilder(get(),ProfileDatabase::class.java, "Profile.db").build()}
    single{ get<ProfileDatabase>().profileDao()}
}