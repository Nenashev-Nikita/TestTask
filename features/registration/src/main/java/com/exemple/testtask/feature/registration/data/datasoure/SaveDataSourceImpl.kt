package com.exemple.testtask.feature.registration.data.datasoure

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

class SaveDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
    private val NameKey: Preferences.Key<String>
) : SaveDataSource {
    override suspend fun getSave(name: String){
        dataStore.edit { preferences ->
            preferences[NameKey] = name
        }
    }
}