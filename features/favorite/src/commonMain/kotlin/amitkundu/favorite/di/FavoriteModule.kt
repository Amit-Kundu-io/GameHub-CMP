package amitkundu.favorite.di

import amitkundu.favorite.ui.FavoriteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getFavoriteModule() = module {
    viewModel {
        FavoriteViewModel(get())
    }
}