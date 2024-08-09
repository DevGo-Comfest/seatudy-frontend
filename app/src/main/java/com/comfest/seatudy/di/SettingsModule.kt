package com.comfest.seatudy.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {
    @Singleton
    @Provides
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.dataStore
    }

    @Singleton
    @Provides
    fun provideSettingsPreferences(dataStore: DataStore<Preferences>): SettingsPreferences {
        return SettingsPreferences(dataStore)
    }
}