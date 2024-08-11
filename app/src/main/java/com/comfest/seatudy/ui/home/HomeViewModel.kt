package com.comfest.seatudy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val repo: SeatudyRepository
) : ViewModel() {

    fun getCourse() = repo.getCourses()



    fun getToken(): LiveData<String> {
        return pref.getTokenUser().asLiveData()
    }
}