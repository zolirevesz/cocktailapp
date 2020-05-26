package com.hnpper.cocktailapp.ui.search

import co.zsmb.rainbowcake.withIOContext
import com.hnpper.cocktailapp.interactors.FirebaseInteractor
import com.hnpper.cocktailapp.model.User
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val firebaseInteractor: FirebaseInteractor
) {
    suspend fun getUser(): User = withIOContext {
        firebaseInteractor.getUser()
    }
}

