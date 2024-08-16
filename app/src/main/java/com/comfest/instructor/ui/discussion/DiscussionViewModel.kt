package com.comfest.instructor.ui.discussion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.comfest.instructor.data.InstructorRepository
import com.comfest.instructor.domain.model.RequestCreateDiscussion
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DiscussionViewModel @Inject constructor(
    private val instructorRepository: InstructorRepository,
    private val pref: SettingsPreferences
): ViewModel(){

    fun getDiscussion(courseId: Int, token: String) = instructorRepository.getDiscussion(courseId, "Bearer $token")

    fun createMessageDiscussion(token: String, requestCreateDiscussion: RequestCreateDiscussion) = instructorRepository.createMessageDiscussion("Bearer $token", requestCreateDiscussion)

    fun getToken(): LiveData<String> {
        return pref.getTokenUser().asLiveData()
    }
}