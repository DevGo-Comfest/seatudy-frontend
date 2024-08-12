package com.comfest.seatudy.ui.dashboard.detailcourse

import androidx.lifecycle.ViewModel
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CourseDetailViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val repo: SeatudyRepository
) : ViewModel() {

    fun getCoursesWithID(id: String) = repo.getCoursesWithID(id)

}