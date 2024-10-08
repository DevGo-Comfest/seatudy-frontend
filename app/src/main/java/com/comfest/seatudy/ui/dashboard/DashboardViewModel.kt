package com.comfest.seatudy.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val repo: SeatudyRepository
) : ViewModel() {

    fun getCoursesWithID(id: String) = repo.getCoursesWithID(id)

    fun getEnrolledCourse(token: String) = repo.getEnrolledCourse(token)

    fun getProgress(id: String, token: String) = repo.getProgress(id, token)

    fun getToken(): LiveData<String> {
        return pref.getTokenUser().asLiveData()
    }
}