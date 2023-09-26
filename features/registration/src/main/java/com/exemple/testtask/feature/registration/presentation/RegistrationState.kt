package com.exemple.testtask.feature.registration.presentation

import java.util.Date

sealed class RegistrationState {
    object Loading : RegistrationState()
    data class Content(
        val name: String,
        val surname: String,
        val data: Long,
        val password: String,
        val confirm_password: String,
        val error: RegistrationErrorState,
    ) : RegistrationState()
}