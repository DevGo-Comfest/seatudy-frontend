package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreateCourseResponse(
    @field:SerializedName("course")
    val course: Course,

    @field:SerializedName("message")
    val message: String
)

data class Course(
    @field:SerializedName("CourseID")
    val CourseID: Int,

    @field:SerializedName("PrimaryAuthor")
    val PrimaryAuthor: String,

    @field:SerializedName("Title")
    val Title: String,

    @field:SerializedName("Description")
    val Description: String,

    @field:SerializedName("Price")
    val Price: Int,

    @field:SerializedName("Category")
    val Category: String,

    @field:SerializedName("ImageURL")
    val ImageURL: String,

    @field:SerializedName("DifficultyLevel")
    val DifficultyLevel: String,

    @field:SerializedName("CreatedDate")
    val CreatedDate: String,

    @field:SerializedName("UpdatedAt")
    val UpdatedAt: String,

    @field:SerializedName("Rating")
    val Rating: Int,

    @field:SerializedName("Status")
    val Status: String,

    @field:SerializedName("IsDeleted")
    val IsDeleted: Boolean,

    @field:SerializedName("Syllabuses")
    val Syllabuses: Any?, // Adjust type based on actual data

    @field:SerializedName("Enrollments")
    val Enrollments: Any?, // Adjust type based on actual data

    @field:SerializedName("Progresses")
    val Progresses: Any?, // Adjust type based on actual data

    @field:SerializedName("ForumPosts")
    val ForumPosts: Any?, // Adjust type based on actual data

    @field:SerializedName("Reviews")
    val Reviews: Any? // Adjust type based on actual data
)
