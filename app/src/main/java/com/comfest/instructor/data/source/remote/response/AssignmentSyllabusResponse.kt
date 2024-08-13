package com.comfest.instructor.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

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


@Parcelize
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
    val submissions: @RawValue Any?,

    @SerializedName("UserAssignments")
    val userAssignments: @RawValue Any?
): Parcelable

