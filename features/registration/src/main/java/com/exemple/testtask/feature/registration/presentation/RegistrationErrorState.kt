package com.exemple.testtask.feature.registration.presentation

sealed class RegistrationErrorState {
    object NotError: RegistrationErrorState()
    object Name: RegistrationErrorState()
    object Surname: RegistrationErrorState()
    object Data: RegistrationErrorState()
    object Password: RegistrationErrorState()
    object ConfirmPassword: RegistrationErrorState()
    object NotErrorName: RegistrationErrorState()
    object NotErrorSurname: RegistrationErrorState()
    object NotErrorData: RegistrationErrorState()
    object NotErrorPassword: RegistrationErrorState()
    object NotErrorConfirmPassword: RegistrationErrorState()
}