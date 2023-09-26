package com.example.testtask.di

import com.example.testtask.contract.MainScreenNavigationImpl
import com.example.testtask.contract.RegistrationNavigationImpl
import com.example.testtask.navigation.buildCicerone
import com.exemple.testtask.feature.presentation.MainScreenNavigation
import com.exemple.testtask.feature.registration.presentation.RegistrationNavigation
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module

val appModule = module {
    single { buildCicerone() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    factory<RegistrationNavigation> {
        RegistrationNavigationImpl(get())
    }
    factory<MainScreenNavigation> {
        MainScreenNavigationImpl(get())
    }
}