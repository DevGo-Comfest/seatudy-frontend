package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateAssignmentResponse(
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
    val submissions: List<Any>,

    @SerializedName("UserAssignments")
    val userAssignments: Any?
)
