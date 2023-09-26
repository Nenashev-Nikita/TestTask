package com.example.testtask.navigation

import com.exemple.testtask.feature.MainScreen
import com.exemple.testtask.feature.registration.RegistrationScreen
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

fun buildCicerone(): Cicerone<Router> =
    Cicerone.create().apply {
        router.newRootScreen(MainScreen.getScreen())
    }