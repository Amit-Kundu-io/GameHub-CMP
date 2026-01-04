package amitkundu.coreNetwork.di


import amitkundu.coreNetwork.client.KtorClient
import org.koin.dsl.module

val getNetworkModule = module {
    single { KtorClient.getInstance() }
}
