package com.comfest.instructor.ui.sylabus

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.comfest.instructor.data.InstructorRepository
import com.comfest.instructor.domain.model.RequestCreateAssignment
import com.comfest.instructor.domain.model.RequestCreateCourse
import com.comfest.instructor.domain.model.RequestCreateSyllabus
import com.comfest.instructor.domain.model.RequestCreateSyllabusMaterial
import com.comfest.instructor.domain.model.RequestUpdateSyllabus
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SyllabusViewModel @Inject constructor(
    private val instructorRepository: InstructorRepository,
    private val pref: SettingsPreferences
): ViewModel() {


    fun createSyllabus(token: String, requestCreateSyllabus: RequestCreateSyllabus) = instructorRepository.createSyllabus("Bearer $token", requestCreateSyllabus)
    fun createAssignment(id: Int, token: String, requestCreateAssignment: RequestCreateAssignment) = instructorRepository.createAssignment(id, "Bearer $token", requestCreateAssignment)

    fun getDetailCourse(courseId: Int, token: String) = instructorRepository.getDetailCourse(courseId, "Bearer $token")

    fun getAssignmentBySyllabusId(syllabusId: Int, token: String) = instructorRepository.getAssignmentBySyllabusId(syllabusId, "Bearer $token")

    fun updateSyllabus(syllabusId: Int,token: String, requestUpdateSyllabus: RequestUpdateSyllabus) = instructorRepository.updateSyllabus(syllabusId, "Bearer $token", requestUpdateSyllabus)

    fun deleteSyllabus(syllabusId: Int,token: String) = instructorRepository.deleteSyllabus(syllabusId, "Bearer $token")

    fun updateAssignment(assignmentId: Int,token: String, requestCreateAssignment: RequestCreateAssignment) = instructorRepository.updateAssignment(assignmentId, "Bearer $token", requestCreateAssignment)

    fun deleteAssignment(assignmentId: Int,token: String) = instructorRepository.deleteAssignment(assignmentId, "Bearer $token")

    fun createSyllabusMaterial(token: String, requestCreateSyllabusMaterial: RequestCreateSyllabusMaterial) = instructorRepository.createSyllabusMaterial("Bearer $token", requestCreateSyllabusMaterial)

    fun getSyllabusMaterialById(syllabusId: Int,token: String) = instructorRepository.getSyllabusMaterialById(syllabusId, "Bearer $token")

    fun getToken(): LiveData<String> {
        return pref.getTokenUser().asLiveData()
    }
}