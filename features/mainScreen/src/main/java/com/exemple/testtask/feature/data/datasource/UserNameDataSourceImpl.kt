package com.exemple.testtask.feature.data.datasource

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.map

class UserNameDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
    private val nameKey: Preferences.Key<String>,
) : UserNameDataSource {
    override fun getUserName(): Flow<String> =
        dataStore.data.map { preferences ->
            preferences[nameKey] ?: "Not name"
        }

}