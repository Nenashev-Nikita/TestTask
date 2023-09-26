package com.example.testtask.contract

import com.exemple.testtask.feature.MainScreen
import com.exemple.testtask.feature.registration.RegistrationScreen
import com.exemple.testtask.feature.registration.presentation.RegistrationNavigation
import com.github.terrakok.cicerone.Router

class RegistrationNavigationImpl(private val router: Router) : RegistrationNavigation {
    override fun goToMainScreen(name: String) {
        router.navigateTo(MainScreen.getScreen())
    }
}