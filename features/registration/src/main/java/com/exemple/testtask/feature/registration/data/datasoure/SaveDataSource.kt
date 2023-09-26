package com.exemple.testtask.feature.registration.data.datasoure

interface SaveDataSource {
    suspend fun getSave(name: String)
}