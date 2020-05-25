package com.hnpper.cocktailapp.ui.registration

import android.net.Uri
import co.zsmb.rainbowcake.base.JobViewModel
import com.hnpper.cocktailapp.model.User
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registrationPresenter: RegistrationPresenter
) : JobViewModel<RegistrationViewState>(RegistrationStarted) {

    fun registerWithEmail(user: User, password: String, photoUri: Uri?) = execute {
        viewState = RegistrationInProgress

        if (registrationPresenter.registerWithEmail(user, password, photoUri)) {
            viewState = RegistrationReady
        } else {
            viewState = RegistrationFailed
        }
    }

}