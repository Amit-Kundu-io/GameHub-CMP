package amitkundu.home.di

import amitkundu.home.data.Apis.GameApis
import amitkundu.home.data.repoImpl.GameRepoImpl
import amitkundu.home.domain.repo.GameRepo
import amitkundu.home.domain.use_case.Get_Game_Use_Case.GetGameUseCase
import amitkundu.home.ui.GameScreen.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getHomeModule() = module {

    single { GameApis(get()) }

    single<GameRepo> {
        GameRepoImpl(get())
    }

    factory { GetGameUseCase(get()) }

    viewModel {
        HomeViewModel(get())
    }
}