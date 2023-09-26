package com.exemple.testtask.feature.domain.usecase

import com.exemple.testtask.feature.domain.repository.GetUserNameRepository
import kotlinx.coroutines.flow.Flow

class GetUserNameUseCase(private val getUserNameRepository: GetUserNameRepository) {
    operator fun invoke(): Flow<String> = getUserNameRepository.getUserName()
}