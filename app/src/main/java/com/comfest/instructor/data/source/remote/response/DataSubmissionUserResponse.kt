package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DataSubmissionUserResponse(
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
    val submissions: List<Submission>,

    @SerializedName("UserAssignments")
    val userAssignments: Any?
)


data class Submission(
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
