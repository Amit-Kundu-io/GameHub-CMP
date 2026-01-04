package org.amitkundu.gamehub

import android.app.Application
import org.amitkundu.gamehub.di.initKoin

class BaseApplication  : Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}