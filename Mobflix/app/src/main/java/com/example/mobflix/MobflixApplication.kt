package com.example.mobflix

import android.app.Application
import com.example.mobflix.di.modules.mobflixViewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MobflixApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MobflixApplication)
            modules(
                listOf(
                    mobflixViewModelModules,
                )
            )
        }
    }
}
