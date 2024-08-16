package com.comfest.seatudy.data.source.respon

import com.google.gson.annotations.SerializedName

data class ResponseSubmissions(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("submission")
    val submission: ResponseSubmissionDetail
)

data class ResponseSubmissionDetail(
    @field:SerializedName("SubmissionID")
    val submissionID: Int,

    @field:SerializedName("Status")
    val status: String,

    @field:SerializedName("Grade")
    val grade: Int,

    @field:SerializedName("ContentURL")
    val contentURL: String,

    @field:SerializedName("IsLate")
    val isLate: Boolean,

    @field:SerializedName("AssignmentID")
    val sssignmentID: Int,

    @field:SerializedName("UserID")
    val userID: String,

    @field:SerializedName("CreatedAt")
    val createdAt: String,

    @field:SerializedName("UpdatedAt")
    val updatedAt: String
)
