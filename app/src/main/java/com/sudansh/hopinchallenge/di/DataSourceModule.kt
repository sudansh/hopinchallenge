package com.sudansh.hopinchallenge.di

import com.sudansh.hopinchallenge.data.HopinDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { HopinDataSource(get()) }
}