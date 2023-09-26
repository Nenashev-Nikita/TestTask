package com.exemple.testtask.feature.presentation

sealed class MainScreenState {
    object Loading : MainScreenState()
    data class Content(
        val Name: String
    ) : MainScreenState()
}