package com.comfest.instructor.ui.course


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.comfest.instructor.data.InstructorRepository
import com.comfest.instructor.domain.model.RequestCreateCourse
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val instructorRepository: InstructorRepository,
    private val pref: SettingsPreferences
): ViewModel() {

    fun uploadImage(token: String, image: MultipartBody.Part) = instructorRepository.uploadImage("Bearer $token", image)
    fun crateCourse(token: String, requestCreateCourse: RequestCreateCourse) = instructorRepository.createCourse("Bearer $token", requestCreateCourse)
    fun updateCourse(id: Int, token: String, requestCreateCourse: RequestCreateCourse) = instructorRepository.updateCourse(id, "Bearer $token", requestCreateCourse)

    fun getToken(): LiveData<String> {
        return pref.getTokenUser().asLiveData()
    }

}