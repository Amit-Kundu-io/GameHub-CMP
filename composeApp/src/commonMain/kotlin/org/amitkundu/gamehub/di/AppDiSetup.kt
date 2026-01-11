package org.amitkundu.gamehub.di

import amitkundu.coreNetwork.di.getNetworkModule
import amitkundu.database.di.getDatabaseModule
import amitkundu.home.di.getHomeModule
import amitkundu.search.di.getSearchModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin


fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null){
    startKoin {
        koinApplication?.invoke(this)
        modules(
            getNetworkModule,
            getHomeModule(),
            getSearchModule(),
            getDatabaseModule()
        )
    }
}