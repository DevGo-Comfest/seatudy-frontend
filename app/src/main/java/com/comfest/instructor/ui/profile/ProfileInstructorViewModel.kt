package com.comfest.instructor.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileInstructorViewModel @Inject constructor(
    private val pref: SettingsPreferences
) : ViewModel() {

    fun saveThemeSetting(state: Boolean) {
        viewModelScope.launch {
            pref.saveLoginUser(state)
        }
    }

}