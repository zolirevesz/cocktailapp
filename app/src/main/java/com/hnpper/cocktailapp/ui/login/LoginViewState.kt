package com.hnpper.cocktailapp.ui.login

import com.hnpper.cocktailapp.model.User

sealed class LoginViewState

object Loading: LoginViewState()

data class LoginSuccessful(val user: User): LoginViewState()

object LoginUnsuccessful: LoginViewState()