package com.comfest.seatudy.data.source.respon

import com.google.gson.annotations.SerializedName

data class ResponseAssignments(
    @field:SerializedName("AssignmentID")
    val assignmentID: Int,

    @field:SerializedName("SyllabusID")
    val syllabusID: Int,

    @field:SerializedName("Title")
    val title: String,

    @field:SerializedName("Description")
    val description: String,

    @field:SerializedName("MaximumTime")
    val maximumTime: Int,

    @field:SerializedName("Submissions")
    val submissions: String,

    @field:SerializedName("UserAssignments")
    val userAssignments: String
)
