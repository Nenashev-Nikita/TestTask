package com.exemple.testtask.feature.di

import com.exemple.testtask.feature.data.datasource.UserNameDataSource
import com.exemple.testtask.feature.data.datasource.UserNameDataSourceImpl
import com.exemple.testtask.feature.data.repository.GetUserNameRepositoryImpl
import com.exemple.testtask.feature.domain.repository.GetUserNameRepository
import com.exemple.testtask.feature.domain.usecase.GetUserNameUseCase
import com.exemple.testtask.feature.presentation.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreenModule = module {
    factory<UserNameDataSource> {
        UserNameDataSourceImpl(
            dataStore = get(),
            nameKey = get(),
        )
    }

    factory<GetUserNameRepository> { GetUserNameRepositoryImpl(dataSource = get()) }

    factory { GetUserNameUseCase(getUserNameRepository = get()) }

    viewModel {
        MainScreenViewModel(
            getUserNameUseCase = get(),
            router = get()
        )
    }
}