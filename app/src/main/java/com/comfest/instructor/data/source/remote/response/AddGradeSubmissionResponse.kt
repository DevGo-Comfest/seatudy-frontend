package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AddGradeSubmissionResponse(
    @SerializedName("SubmissionID")
    val submissionID: Int,

    @SerializedName("Status")
    val status: String,

    @SerializedName("Grade")
    val grade: Int,

    @SerializedName("ContentURL")
    val contentURL: String,

    @SerializedName("IsLate")
    val isLate: Boolean,

    @SerializedName("AssignmentID")
    val assignmentID: Int,

    @SerializedName("UserID")
    val userID: String,

    @SerializedName("CreatedAt")
    val createdAt: String,

    @SerializedName("UpdatedAt")
    val updatedAt: String
)
