package amitkundu.search.di

import amitkundu.search.data.apis.SearchApis
import amitkundu.search.data.repo_impl.SearchRepoImpl
import amitkundu.search.domain.repo.SearchRepo
import amitkundu.search.domain.use_case.SearchUseCase
import amitkundu.search.ui.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getSearchModule() = module {
    single { SearchApis(get()) }

    single<SearchRepo> {
        SearchRepoImpl(get())
    }

    factory { SearchUseCase(get()) }

    viewModel {
        SearchViewModel(get())
    }
}