package com.comfest.instructor.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.comfest.instructor.data.InstructorRepository
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeInstructorViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val instructorRepository: InstructorRepository
): ViewModel() {


    fun getCourse(token: String) = instructorRepository.getCourse("Bearer $token")

    fun activatedCourse(id: Int, token: String) = instructorRepository.activatedCourse(id, "Bearer $token")

    fun getToken(): LiveData<String> {
        return pref.getTokenUser().asLiveData()
    }

}