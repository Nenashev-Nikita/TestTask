package com.exemple.testtask.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import org.koin.dsl.module

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "admin")
private val NAME = stringPreferencesKey("admin")

val dataModule = module {
    single { get<Context>().dataStore }
    single { NAME }
}