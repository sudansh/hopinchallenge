package com.sudansh.hopinchallenge

import android.app.Application
import com.sudansh.hopinchallenge.di.dataSourceModule
import com.sudansh.hopinchallenge.di.remoteModule
import com.sudansh.hopinchallenge.di.usecaseModule
import com.sudansh.hopinchallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            // declare modules
            loadKoinModules(
                listOf(
                    remoteModule,
                    viewModelModule,
                    usecaseModule,
                    dataSourceModule
                )
            )
        }
    }
}