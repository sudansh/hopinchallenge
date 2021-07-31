package com.sudansh.hopinchallenge.di

import com.sudansh.hopinchallenge.domain.GetStreamUrlUseCase
import com.sudansh.hopinchallenge.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val usecaseModule = module {
    factory { GetStreamUrlUseCase(get()) }
}