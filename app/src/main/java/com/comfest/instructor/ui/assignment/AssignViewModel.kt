package com.comfest.instructor.ui.assignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.comfest.instructor.data.InstructorRepository
import com.comfest.instructor.domain.model.RequestAssignInstructor
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AssignViewModel @Inject constructor(
    private val instructorRepository: InstructorRepository,
    private val pref: SettingsPreferences
): ViewModel() {

    fun getInstructor() = instructorRepository.getInstructor()

    fun assignInstructor(courseId: Int, token: String, requestAssignInstructor: RequestAssignInstructor) = instructorRepository.assignInstructor(courseId, "Bearer $token", requestAssignInstructor)

    fun getToken(): LiveData<String> {
        return pref.getTokenUser().asLiveData()
    }
}