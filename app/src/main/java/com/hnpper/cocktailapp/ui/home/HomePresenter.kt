package com.hnpper.cocktailapp.ui.home

import co.zsmb.rainbowcake.withIOContext
import javax.inject.Inject

class HomePresenter @Inject constructor() {
    suspend fun getData(): String = withIOContext {
        ""
    }
}