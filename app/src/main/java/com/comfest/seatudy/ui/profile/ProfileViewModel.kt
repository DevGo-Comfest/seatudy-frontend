package com.comfest.seatudy.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comfest.seatudy.data.SeatudyRepository
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val repo: SeatudyRepository
) : ViewModel() {

    fun saveThemeSetting(state: Boolean) {
        viewModelScope.launch {
            pref.saveLoginUser(state)
        }
    }
}

