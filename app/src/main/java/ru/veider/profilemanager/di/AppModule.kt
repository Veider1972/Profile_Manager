package ru.veider.profilemanager.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

val appModule = module {
//    singleOf(::LocalRepoImpl) {bind<LocalRepo>()}
//    singleOf(::RepoImpl) {bind<Repo>()}
//    singleOf(::UseCaseImpl) {bind<UseCase>()}
    singleOf(::PreferenceViewModel)
}
//
//val apiModule = module {
//    fun provideApi(retrofit: Retrofit): ApiStimmulService {
//        return retrofit.create(ApiStimmulService::class.java)
//    }
//    single { provideApi(get()) }
//}
//val netModule = module {
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://stimmul.ru/wp-json/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//    single { provideRetrofit() }
//}
