package com.comfest.seatudy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pref: SettingsPreferences
) : ViewModel() {
    fun getLoginUser(): LiveData<Boolean> {
        return pref.getLoginUser().asLiveData()
    }

}