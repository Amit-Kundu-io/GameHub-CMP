package amitkundu.home.di

import amitkundu.home.data.Apis.GameApis
import amitkundu.home.domain.use_case.Get_Game_Use_Case.GetGameUseCase
import org.koin.dsl.module

fun getHomeModule() = module {

    single { GameApis(get()) }
    factory { GetGameUseCase(get()) }

}