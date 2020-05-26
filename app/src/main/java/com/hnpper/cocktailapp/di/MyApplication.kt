package com.hnpper.cocktailapp.di

import android.app.Application
import co.zsmb.rainbowcake.config.Logger
import co.zsmb.rainbowcake.config.Loggers
import co.zsmb.rainbowcake.config.rainbowCake
import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.timber.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import co.zsmb.rainbowcake.timber.TIMBER
import com.hnpper.cocktailapp.data.disk.DatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

@Suppress("UNUSED")
class MyApplication : RainbowCakeApplication() {

    override lateinit var injector: AppComponent

    @Suppress("DEPRECATION")
    override fun setupInjector() {
        injector = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        rainbowCake {
            isDebug = BuildConfig.DEBUG
        }
    }

}