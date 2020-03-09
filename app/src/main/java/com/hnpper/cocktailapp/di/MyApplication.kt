package com.hnpper.cocktailapp.di

import co.zsmb.rainbowcake.dagger.RainbowCakeApplication

class MyApplication : RainbowCakeApplication() {

    override lateinit var injector: AppComponent

    override fun setupInjector() {
        //injector = DaggerAppComponent.create()
    }

}