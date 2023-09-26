package com.exemple.testtask.feature.data.repository

import com.exemple.testtask.feature.data.datasource.UserNameDataSource
import com.exemple.testtask.feature.domain.repository.GetUserNameRepository
import kotlinx.coroutines.flow.Flow

class GetUserNameRepositoryImpl(private val dataSource: UserNameDataSource): GetUserNameRepository {
    override fun getUserName(): Flow<String> = dataSource.getUserName()
}