package com.hnpper.cocktailapp.ui.registration

import android.net.Uri
import co.zsmb.rainbowcake.withIOContext
import com.hnpper.cocktailapp.interactors.FirebaseInteractor
import com.hnpper.cocktailapp.model.User
import javax.inject.Inject

class RegistrationPresenter @Inject constructor(
    private val firebaseInteractor: FirebaseInteractor
) {

    suspend fun registerWithEmail(user: User, password: String, photoUri: Uri?): Boolean =
        withIOContext {
            firebaseInteractor.registerWithEmail(user, password, photoUri)
        }

}