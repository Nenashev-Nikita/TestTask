package com.exemple.testtask.feature.registration.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exemple.testtask.feature.registration.domain.entity.SaveEntity
import com.exemple.testtask.feature.registration.domain.usecase.SaveNameUseCase
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class RegistrationViewModel(
    private val router: RegistrationNavigation,
    private val saveNameUseCase: SaveNameUseCase,
) : ViewModel() {
    private val _state: MutableLiveData<RegistrationState> =
        MutableLiveData(RegistrationState.Loading)
    val state: LiveData<RegistrationState> = _state

    companion object {
        private val nameRegex = Regex("(?=[A-Za-z]).{3,}|(?=[А-Яа-я]).{3,}")
        private val passwordRegex = Regex("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#\\\$%]).{5,}")
    }

    init {
        _state.value =
            RegistrationState.Content("A", "A", null, "g", "f", RegistrationErrorState.NotError)
    }

    fun checkName(str: String): Boolean {
        val currentState = _state.value as? RegistrationState.Content ?: return false
        if (str.isEmpty()) return false

        return if (!str.matches(nameRegex)) {
            _state.value = currentState.copy(error = RegistrationErrorState.Name)
            false
        } else {
            _state.value =
                currentState.copy(name = str, error = RegistrationErrorState.NotErrorName)
            true
        }
    }

    fun checkSurname(str: String): Boolean {
        val currentState = _state.value as? RegistrationState.Content ?: return false
        if (str.isEmpty()) return false
        return if (str.length < 2) {
            _state.value = currentState.copy(error = RegistrationErrorState.Surname)
            false
        } else {
            _state.value =
                currentState.copy(surname = str, error = RegistrationErrorState.NotErrorSurname)
            true
        }
    }

    fun checkData(data: Date): Boolean {
        val calendar: Calendar = Calendar.getInstance()
        val currentState = _state.value as? RegistrationState.Content ?: return false
        if (data.toString().isEmpty()) return false
        return if (data.time > calendar.time.time) {
            _state.value = currentState.copy(error = RegistrationErrorState.Data)
            false
        } else {
            _state.value = currentState.copy(data = data, error = RegistrationErrorState.NotErrorData)
            true
        }
    }

    fun checkPassword(str: String): Boolean {
        val currentState = _state.value as? RegistrationState.Content ?: return false
        if (str.isEmpty()) return false
        return if (!str.matches(passwordRegex)) {
            _state.value = currentState.copy(error = RegistrationErrorState.Password)
            false
        } else {
            _state.value =
                currentState.copy(password = str, error = RegistrationErrorState.NotErrorPassword)
            true
        }
    }

    fun checkConfirmPassword(str: String): Boolean {
        val currentState = _state.value as? RegistrationState.Content ?: return false
        if (str.isEmpty()) return false
        return if (currentState.password != str) {
            _state.value = currentState.copy(error = RegistrationErrorState.ConfirmPassword)
            false
        } else {
            _state.value = currentState.copy(
                confirm_password = str,
                error = RegistrationErrorState.NotErrorConfirmPassword
            )
            true
        }
    }

    fun broadcast() {
        val currentState = _state.value as? RegistrationState.Content ?: return
        viewModelScope.launch {
            saveNameUseCase(SaveEntity(name = currentState.name))
            router.goToMainScreen(currentState.name)
        }
    }

    fun onCheck(): Boolean {
        val currentState = _state.value as? RegistrationState.Content ?: return false
        return ((currentState.name.isNotEmpty())
                and (currentState.surname.length > 2)
                and (currentState.data.toString().isNotEmpty())
                and (currentState.password.matches(passwordRegex))
                and (currentState.password == currentState.confirm_password)
                )
    }

}