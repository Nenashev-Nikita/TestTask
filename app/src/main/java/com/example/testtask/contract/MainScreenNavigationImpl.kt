package com.example.testtask.contract

import com.exemple.testtask.feature.presentation.MainScreenNavigation
import com.exemple.testtask.feature.registration.RegistrationScreen
import com.github.terrakok.cicerone.Router

class MainScreenNavigationImpl(private val router: Router) : MainScreenNavigation {
    override fun mainScreen() {
        router.navigateTo(RegistrationScreen.getScreen())
    }
}