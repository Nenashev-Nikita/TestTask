package com.exemple.testtask.feature.data.datasource

import kotlinx.coroutines.flow.Flow

interface UserNameDataSource {
    fun getUserName(): Flow<String>
}