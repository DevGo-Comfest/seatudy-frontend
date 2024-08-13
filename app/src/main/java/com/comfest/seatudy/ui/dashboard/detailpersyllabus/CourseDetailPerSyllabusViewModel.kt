package com.comfest.seatudy.ui.dashboard.detailpersyllabus

import androidx.lifecycle.ViewModel
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CourseDetailPerSyllabusViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val repo: SeatudyRepository
) : ViewModel() {
    fun getCoursesWithID(id: String) = repo.getCoursesWithID(id)
    fun getEnrolledCourse(token: String) = repo.getEnrolledCourse(token)

    fun getSyllabus(id: String) = repo.getSyllabus(id)
}