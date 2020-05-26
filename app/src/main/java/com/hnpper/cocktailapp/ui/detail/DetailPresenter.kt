package com.hnpper.cocktailapp.ui.detail

import android.net.Uri
import co.zsmb.rainbowcake.withIOContext
import com.hnpper.cocktailapp.interactors.FirebaseInteractor
import com.hnpper.cocktailapp.model.User
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val firebaseInteractor: FirebaseInteractor) {

    suspend fun getUser(): User = withIOContext {
        firebaseInteractor.getUser()
    }

    suspend fun saveUser(user: User, password: String?, photoUri: Uri?): Boolean = withIOContext {
        firebaseInteractor.saveUser(user, password, photoUri)
    }
}