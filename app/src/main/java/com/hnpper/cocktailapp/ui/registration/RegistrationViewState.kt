package com.hnpper.cocktailapp.ui.registration

sealed class RegistrationViewState

object RegistrationStarted : RegistrationViewState()

object RegistrationInProgress : RegistrationViewState()

object RegistrationReady : RegistrationViewState()

object RegistrationFailed : RegistrationViewState()