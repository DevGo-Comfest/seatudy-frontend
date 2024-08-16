package com.comfest.seatudy.ui.dashboard.detailpersyllabus

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.comfest.seatudy.domain.model.DataAssignment
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CourseDetailPerSyllabusViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val repo: SeatudyRepository
) : ViewModel() {
    fun getUserAssignment(id: String, token: String) = repo.getUserAssignment(id, token)

    fun sendOpenAssignment(dataAssignment: DataAssignment, token: String) = repo.sendOpenAssignment(dataAssignment, token)

    fun getSyllabus(id: String) = repo.getSyllabus(id)

    fun getToken(): LiveData<String> {
        return pref.getTokenUser().asLiveData()
    }
}