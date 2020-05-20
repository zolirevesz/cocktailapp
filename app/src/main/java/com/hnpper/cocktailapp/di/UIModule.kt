package com.hnpper.cocktailapp.di

import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.dagger.ViewModelKey
import com.hnpper.cocktailapp.ui.detail.DetailViewModel
import com.hnpper.cocktailapp.ui.home.HomeViewModel
import com.hnpper.cocktailapp.ui.login.LoginViewModel
import com.hnpper.cocktailapp.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.koin.dsl.module

val UIModule = module {
    factory { HomeViewModel(get()) }

    factory { DetailViewModel() }

    factory { LoginViewModel() }

    factory { SearchViewModel() }

}