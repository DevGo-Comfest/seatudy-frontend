package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AssignmentSyllabusResponse(
    @SerializedName("syllabus")
    val syllabus: SyllabusAssignmentResponse
)


data class SyllabusAssignmentResponse(
    @SerializedName("SyllabusID")
    val syllabusID: Int,

    @SerializedName("Order")
    val order: Int,

    @SerializedName("Title")
    val title: String,

    @SerializedName("Description")
    val description: String,

    @SerializedName("InstructorID")
    val instructorID: String,

    @SerializedName("CourseID")
    val courseID: Int,

    @SerializedName("is_locked")
    val isLocked: Any?,

    @SerializedName("Materials")
    val materials: List<Any>,

    @SerializedName("Assignments")
    val assignments: List<AssignmentSyllabus>
)

data class AssignmentSyllabus(
    @SerializedName("AssignmentID")
    val assignmentID: Int,

    @SerializedName("SyllabusID")
    val syllabusID: Int,

    @SerializedName("Title")
    val title: String,

    @SerializedName("Description")
    val description: String,

    @SerializedName("MaximumTime")
    val maximumTime: Int,

    @SerializedName("Submissions")
    val submissions: Any?,

    @SerializedName("UserAssignments")
    val userAssignments: Any?
)

