package com.exemple.testtask.feature.registration

import com.exemple.testtask.feature.registration.ui.RegistrationFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object RegistrationScreen {

    fun getScreen() = FragmentScreen { RegistrationFragment.newInstance() }
}