package com.exemple.testtask.feature.registration.di

import com.exemple.testtask.feature.registration.data.datasoure.SaveDataSource
import com.exemple.testtask.feature.registration.data.datasoure.SaveDataSourceImpl
import com.exemple.testtask.feature.registration.data.repository.SaveRepositoryImpl
import com.exemple.testtask.feature.registration.domain.repository.SaveRepository
import com.exemple.testtask.feature.registration.domain.usecase.SaveNameUseCase
import com.exemple.testtask.feature.registration.presentation.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registrationModule = module {

    factory<SaveDataSource> {
        SaveDataSourceImpl(
            dataStore = get(),
            NameKey = get(),
        )
    }
    factory<SaveRepository> { SaveRepositoryImpl(dataSource = get()) }
    factory { SaveNameUseCase(saveRepository = get()) }

    viewModel {
        RegistrationViewModel(
            router = get(),
            saveNameUseCase = get(),
        )
    }
}