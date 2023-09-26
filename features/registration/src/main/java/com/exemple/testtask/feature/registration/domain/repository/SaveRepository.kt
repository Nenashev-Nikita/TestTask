package com.exemple.testtask.feature.registration.domain.repository

import com.exemple.testtask.feature.registration.domain.entity.SaveEntity

interface SaveRepository {
    suspend fun getSave(name: SaveEntity)
}