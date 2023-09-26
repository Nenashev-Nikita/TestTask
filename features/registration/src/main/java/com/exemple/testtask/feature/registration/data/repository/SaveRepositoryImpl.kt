package com.exemple.testtask.feature.registration.data.repository

import com.exemple.testtask.feature.registration.data.datasoure.SaveDataSource
import com.exemple.testtask.feature.registration.domain.entity.SaveEntity
import com.exemple.testtask.feature.registration.domain.repository.SaveRepository

class SaveRepositoryImpl(private val dataSource: SaveDataSource) : SaveRepository {
    override suspend fun getSave(name: SaveEntity) {
        dataSource.getSave(name = name.name)
    }
}