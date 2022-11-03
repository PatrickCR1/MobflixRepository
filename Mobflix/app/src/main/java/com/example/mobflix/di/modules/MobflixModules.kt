package com.example.mobflix.di.modules

import com.example.mobflix.service.repository.CategoryRepository
import com.example.mobflix.service.repository.VideoRepository
import com.example.mobflix.service.repository.local.CategoryDAO
import com.example.mobflix.service.repository.local.CategoryDatabase
import com.example.mobflix.service.repository.local.VideoDAO
import com.example.mobflix.service.repository.local.VideoDatabase
import com.example.mobflix.service.repository.remote.RetrofitClient
import com.example.mobflix.service.repository.remote.YoutubeService
import com.example.mobflix.ui.viewmodel.BaseVideoViewModel
import com.example.mobflix.ui.viewmodel.MainViewModel
import com.example.mobflix.ui.viewmodel.VideoEditViewModel
import com.example.mobflix.ui.viewmodel.VideoRegistrationViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val mobflixViewModelModules = module {
    viewModel<MainViewModel> { MainViewModel(get(),get()) }
    viewModel<VideoRegistrationViewModel> { VideoRegistrationViewModel(get(), get(), androidContext()) }
    viewModel<VideoEditViewModel> { VideoEditViewModel(get(), get(), androidContext()) }
}

val videoRepositoryModules = module {
    single<VideoRepository> { VideoRepository(get(), get(), get()) }
    single<CategoryRepository> { CategoryRepository(get()) }
}

val videoDatabaseModules = module {
    single<VideoDatabase> {
        VideoDatabase.getDatabase(androidContext())
    }
    single<VideoDAO> { get<VideoDatabase>().videoDAO }

    single<CategoryDatabase> {
        CategoryDatabase.getDatabase(androidContext())
    }
    single<CategoryDAO> { get<CategoryDatabase>().categoryDAO }
}

val MobflixWebClientModules = module {
    single<OkHttpClient> {
        RetrofitClient.getHttpClientInstance()
    }
    single<Retrofit> {
        RetrofitClient.getRetrofitInstance(get())
    }
    single<YoutubeService> { get<Retrofit>().create(YoutubeService::class.java) }
}
