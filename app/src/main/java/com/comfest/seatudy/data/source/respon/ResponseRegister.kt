package com.comfest.seatudy.data.source.respon

import com.google.gson.annotations.SerializedName

data class ResponseRegister(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("user")
    val user: ResponseUserRegister
)

data class ResponseUserRegister(
    @field:SerializedName("UserID")
    val userID: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("Balance")
    val balance: Int,

    @field:SerializedName("Role")
    val role: String,

    @field:SerializedName("CreatedAt")
    val createdAt: String,

    @field:SerializedName("UpdatedAt")
    val updatedAt: String,

    @field:SerializedName("Topups")
    val topUp: Int,

    @field:SerializedName("Courses")
    val sourses: String,

    @field:SerializedName("Progresses")
    val progresses: String,

    @field:SerializedName("Assignments")
    val assignments: String,

    @field:SerializedName("Reviews")
    val reviews: String,

    @field:SerializedName("ForumPosts")
    val forumPosts: String
)
