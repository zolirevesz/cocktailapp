package com.hnpper.cocktailapp.ui.home

import co.zsmb.rainbowcake.withIOContext
import com.hnpper.cocktailapp.interactors.FirebaseInteractor
import com.hnpper.cocktailapp.model.User
import javax.inject.Inject

class HomePresenter @Inject constructor(private val firebaseInteractor: FirebaseInteractor) {

    suspend fun getUser(): User = withIOContext {
        firebaseInteractor.getUser()
    }

    suspend fun logout(): Boolean = withIOContext {
        firebaseInteractor.logout()
    }
}