package com.hnpper.cocktailapp.ui.login

import co.zsmb.rainbowcake.base.JobViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginPresenter: LoginPresenter
) : JobViewModel<LoginViewState>(LoginStarted) {

    fun signInWithEmail(email: String, password: String) = execute {
        viewState = Loading

        if(loginPresenter.signInWithEmail(email, password)){
            viewState = LoginSuccessful(loginPresenter.getUser())
        } else {
            viewState = LoginUnsuccessful
        }
    }

    fun existActiveUser() = execute {
        viewState = Loading

        if (loginPresenter.existActiveUser()) {
            viewState = LoginSuccessful(loginPresenter.getUser())
        } else {
            viewState = LoginUnsuccessful
        }
    }
}