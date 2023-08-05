package com.banit.chewchase

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var application: Application
            private set
    }
}