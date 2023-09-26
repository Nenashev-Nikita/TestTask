package com.exemple.testtask.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exemple.testtask.feature.domain.entity.GetUserNameEntity
import com.exemple.testtask.feature.domain.usecase.GetUserNameUseCase
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val router: MainScreenNavigation
) : ViewModel() {
    private val _state: MutableLiveData<MainScreenState> = MutableLiveData(MainScreenState.Loading)
    val state: LiveData<MainScreenState> = _state

    init {
        _state.value = MainScreenState.Content("")
    }

    fun getUserName() {
        val currentState = _state.value as? MainScreenState.Content ?: return
        viewModelScope.launch {
            val name = getUserNameUseCase().first()
            if (name == "Not name") {
                getRegistration()
            }else{
                _state.value = currentState.copy(Name = name)
            }
        }
    }

    fun getRegistration() {
        router.mainScreen()
    }
}