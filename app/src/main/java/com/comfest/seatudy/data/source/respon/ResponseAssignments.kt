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

data class ResponseOpenAssignment(
    @field:SerializedName("message")
    val message: String
)

data class ResponseUserAssignment(
    @field:SerializedName("UserAssignmentID")
    val userAssignmentID: Int,

    @field:SerializedName("AssignmentID")
    val assignmentID: Int,

    @field:SerializedName("UserID")
    val userID: String,


    @field:SerializedName("DueDate")
    val dueDate: String,

    @field:SerializedName("CreatedAt")
    val createdAt: String,
)