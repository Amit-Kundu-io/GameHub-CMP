package org.amitkundu.gamehub

import android.app.Application
import org.amitkundu.gamehub.di.initKoin
import org.koin.android.ext.koin.androidContext

class BaseApplication  : Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin(
            koinApplication = {koinApp ->
                koinApp.androidContext(this@BaseApplication)
            }
        )
    }
}