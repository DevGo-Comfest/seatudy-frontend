package com.comfest.seatudy.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.comfest.seatudy.domain.model.DataTopUp
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val repo: SeatudyRepository
) : ViewModel() {

    fun getProfile(token: String) = repo.getProfile(token)

    fun topUp(balance: DataTopUp, token: String) = repo.topUp(balance, token)

    fun deleteLoginUser(state: Boolean) {
        viewModelScope.launch {
            pref.saveLoginUser(state)
        }
    }

    fun deleteToken(token: String) {
        viewModelScope.launch {
            pref.saveTokenUser(token)
        }
    }

    fun deleteRoleUser(role: String) {
        viewModelScope.launch {
            pref.saveRoleUser(role)
        }
    }

    fun deleteName(name: String) {
        viewModelScope.launch {
            pref.saveNameUser(name)
        }
    }

    fun getToken(): LiveData<String> {
        return pref.getTokenUser().asLiveData()
    }
}

