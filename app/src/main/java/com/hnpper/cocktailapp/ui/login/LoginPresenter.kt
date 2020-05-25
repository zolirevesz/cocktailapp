package com.hnpper.cocktailapp.ui.login

import co.zsmb.rainbowcake.withIOContext
import com.hnpper.cocktailapp.interactors.FirebaseInteractor
import com.hnpper.cocktailapp.model.User
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val firebaseInteractor: FirebaseInteractor
) {

    suspend fun signInWithEmail(email: String, password: String): Boolean = withIOContext {
        firebaseInteractor.signInWithEmail(email, password)
    }

    suspend fun existActiveUser(): Boolean = withIOContext {
        firebaseInteractor.existActiveUser()
    }

    suspend fun getUser(): User = withIOContext {
        firebaseInteractor.getUser()
    }
}
