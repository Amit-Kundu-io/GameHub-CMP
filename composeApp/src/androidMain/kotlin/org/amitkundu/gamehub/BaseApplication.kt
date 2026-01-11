package org.amitkundu.gamehub

import amitkundu.database.DatabaseFactory
import android.app.Application
import org.amitkundu.gamehub.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin { koinApp ->
            koinApp.androidContext(this@BaseApplication)

            koinApp.modules(
                module {
                    single { DatabaseFactory(this@BaseApplication) }
                }
            )
        }
    }
}
