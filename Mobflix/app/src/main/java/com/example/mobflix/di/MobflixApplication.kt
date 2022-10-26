package com.example.mobflix.di

import android.app.Application
import com.example.mobflix.di.modules.MobflixWebClientModules
import com.example.mobflix.di.modules.mobflixViewModelModules
import com.example.mobflix.di.modules.videoDatabaseModules
import com.example.mobflix.di.modules.videoRepositoryModules
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
                    videoRepositoryModules,
                    videoDatabaseModules,
                    MobflixWebClientModules,
                )
            )
        }
    }
}
