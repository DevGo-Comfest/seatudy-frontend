package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreateAssignmentResponse(
    @SerializedName("assignment")
    val assignment: Assignment,

    @SerializedName("message")
    val message: String
)

data class Assignment(
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
