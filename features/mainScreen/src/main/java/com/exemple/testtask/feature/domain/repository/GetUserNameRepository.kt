package com.exemple.testtask.feature.domain.repository

import kotlinx.coroutines.flow.Flow

interface GetUserNameRepository {
    fun getUserName(): Flow<String>
}