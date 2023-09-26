package com.exemple.testtask.feature

import com.exemple.testtask.feature.ui.MainScreenFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object MainScreen {

    fun getScreen() = FragmentScreen { MainScreenFragment.newInstance()}
}