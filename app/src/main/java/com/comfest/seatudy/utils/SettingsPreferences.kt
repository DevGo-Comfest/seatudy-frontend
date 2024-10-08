package com.comfest.seatudy.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val loginUser = booleanPreferencesKey("login_user")
    private val nameUser = stringPreferencesKey("name_user")
    private val tokenUser = stringPreferencesKey("token_user")
    private val roleUser = stringPreferencesKey("role_user")

    fun getLoginUser(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[loginUser] ?: false
        }
    }

    fun getName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[nameUser] ?: ""
        }
    }

    fun getRoleUser(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[roleUser] ?: ""
        }
    }

    fun getTokenUser(): Flow<String> {
        return dataStore.data.map { preferences->
            preferences[tokenUser] ?: ""
        }
    }

    suspend fun saveRoleUser(role: String) {
        dataStore.edit { preferences ->
            preferences[roleUser] = role
        }
    }


    suspend fun saveTokenUser(token: String){
        dataStore.edit { preferences->
            preferences[tokenUser] = token
        }
    }

    suspend fun saveLoginUser(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[loginUser] = isDarkModeActive
        }
    }

    suspend fun saveNameUser(name: String) {
        dataStore.edit { preferences ->
            preferences[nameUser] = name
        }
    }

}