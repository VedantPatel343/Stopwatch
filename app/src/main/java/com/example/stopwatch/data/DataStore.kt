package com.example.stopwatch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.stopwatch.util.Constants
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {

    companion object {
        private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "DataStore")
        private val APP_THEME = stringPreferencesKey("theme_preferences_key")
    }

    suspend fun updateAppTheme(theme: String) {
        context.preferencesDataStore.edit { preferences ->
            preferences[APP_THEME] = theme
        }
    }

    fun getAppTheme() = context.preferencesDataStore.data.map { preferences ->
        preferences[APP_THEME] ?: Constants.AppTheme.CLASSIC
    }

}