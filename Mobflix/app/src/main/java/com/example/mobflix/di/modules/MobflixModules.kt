package com.example.mobflix.di.modules

import com.example.mobflix.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mobflixViewModelModules = module {
viewModel<MainViewModel> { MainViewModel() }
}
