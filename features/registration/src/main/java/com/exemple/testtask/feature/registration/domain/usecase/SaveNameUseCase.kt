package com.exemple.testtask.feature.registration.domain.usecase

import com.exemple.testtask.feature.registration.domain.entity.SaveEntity
import com.exemple.testtask.feature.registration.domain.repository.SaveRepository

class SaveNameUseCase(private val saveRepository: SaveRepository) {
    suspend operator fun invoke(name: SaveEntity) =
        saveRepository.getSave(name)
}