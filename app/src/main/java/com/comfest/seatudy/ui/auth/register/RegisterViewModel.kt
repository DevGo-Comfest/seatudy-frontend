package com.comfest.seatudy.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comfest.seatudy.data.SeatudyRepository
import com.comfest.seatudy.domain.model.DataRegister
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val repo: SeatudyRepository
) : ViewModel() {

    fun register(data: DataRegister) = repo.register(data)

}