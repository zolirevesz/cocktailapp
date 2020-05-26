package com.hnpper.cocktailapp.ui.login

import com.hnpper.cocktailapp.model.User

sealed class LoginViewState

object LoginStarted : LoginViewState()

object Loading: LoginViewState()

object LoginReady: LoginViewState()

data class LoginSuccessful(val user: User): LoginViewState()

object LoginUnsuccessful: LoginViewState()