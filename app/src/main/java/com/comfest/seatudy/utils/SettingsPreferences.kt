package com.comfest.seatudy.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val LOGIN_USER = booleanPreferencesKey("login_user")

    fun getLoginUser(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[LOGIN_USER] ?: false
        }
    }

    suspend fun saveLoginUser(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[LOGIN_USER] = isDarkModeActive
        }
    }
}