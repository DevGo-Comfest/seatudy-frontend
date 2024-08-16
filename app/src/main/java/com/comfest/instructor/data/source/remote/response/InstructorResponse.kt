package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class InstructorResponse(
    @SerializedName("instructors")
    val instructors: List<Instructor>
)

data class Instructor(
    @SerializedName("UserID")
    val userID: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("Balance")
    val balance: Int,

    @SerializedName("Role")
    val role: String,

    @SerializedName("CreatedAt")
    val createdAt: String,

    @SerializedName("UpdatedAt")
    val updatedAt: String,

    @SerializedName("Topups")
    val topups: Any?,

    @SerializedName("Courses")
    val courses: Any?,

    @SerializedName("AuthoredCourses")
    val authoredCourses: Any?,

    @SerializedName("Progresses")
    val progresses: Any?,

    @SerializedName("Submissions")
    val submissions: Any?,

    @SerializedName("UserAssignments")
    val userAssignments: Any?,

    @SerializedName("Reviews")
    val reviews: Any?,

    @SerializedName("ForumPosts")
    val forumPosts: Any?
)
