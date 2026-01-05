package amitkundu.home.di

import amitkundu.home.data.Apis.GameApis
import org.koin.dsl.module

fun getHomeModule() = module {

    single { GameApis(get()) }

}