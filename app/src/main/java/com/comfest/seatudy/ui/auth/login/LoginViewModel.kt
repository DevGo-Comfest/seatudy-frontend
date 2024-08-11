package com.comfest.seatudy.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comfest.seatudy.data.SeatudyRepository
import com.comfest.seatudy.domain.model.DataLogin
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val repo: SeatudyRepository
) : ViewModel() {

    fun login(data: DataLogin) = repo.login(data)

    fun saveThemeSetting(state: Boolean) {
        viewModelScope.launch {
            pref.saveLoginUser(state)
        }
    }

    fun saveTokenUser(token: String) {
        viewModelScope.launch {
            pref.saveTokenUser(token)
        }
    }


    fun saveRoleUser(role: String) {
        viewModelScope.launch {
            pref.saveRoleUser(role)
        }
    }
}