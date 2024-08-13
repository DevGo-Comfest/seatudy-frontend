package com.comfest.seatudy.data.source.respon

import com.google.gson.annotations.SerializedName


data class ResponseSyllabus(
    @field:SerializedName("syllabus")
    val syllabus: ResponseSyllabuses,
)

data class ResponseSyllabuses(
    @field:SerializedName("SyllabusID")
    val syllabusID: Int,

    @field:SerializedName("Order")
    val order: Int,

    @field:SerializedName("Title")
    val title: String,

    @field:SerializedName("Description")
    val description: String,

    @field:SerializedName("InstructorID")
    val instructorID: String,

    @field:SerializedName("CourseID")
    val courseID: Int,

    @field:SerializedName("is_locked")
    val isLocked: Boolean,

    @field:SerializedName("Materials")
    val materials: List<ResponseMaterials>,

    @field:SerializedName("Assignments")
    val assignments: List<ResponseAssignments>

)