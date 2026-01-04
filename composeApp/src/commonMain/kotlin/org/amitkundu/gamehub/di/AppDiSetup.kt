package org.amitkundu.gamehub.di

import amitkundu.coreNetwork.di.getNetworkModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin


fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null){
    startKoin {
        modules(
            getNetworkModule
        )
    }
}