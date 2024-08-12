package com.comfest.seatudy.data.source.respon

import com.google.gson.annotations.SerializedName

data class ResponseEnrollCourse(
    @field:SerializedName("enrolled_courses")
    val courses: List<ResponseEnrolledCourse>
)

data class ResponseEnrolledCourse(
    @field:SerializedName("CourseID")
    val courseID: Int,

    @field:SerializedName("UserID")
    val userID: String,

    @field:SerializedName("Title")
    val title: String,

    @field:SerializedName("Description")
    val description: String,

    @field:SerializedName("Price")
    val price: Int,

    @field:SerializedName("Category")
    val category: String,

    @field:SerializedName("ImageURL")
    val imageURL: String,

    @field:SerializedName("DifficultyLevel")
    val difficultyLevel: String,

    @field:SerializedName("CreatedDate")
    val createdDate: String,

    @field:SerializedName("UpdatedAt")
    val updatedAt: String,

    @field:SerializedName("Rating")
    val rating: Int,

    @field:SerializedName("Status")
    val status: String,

    @field:SerializedName("IsDeleted")
    val isDeleted: Boolean,

    @field:SerializedName("Syllabuses")
    val syllabuses: List<ResponseSyllabuses>,

    @field:SerializedName("Enrollments")
    val enrollments: String,

    @field:SerializedName("Progresses")
    val progresses: String,

    @field:SerializedName("ForumPosts")
    val forumPosts: String,

    @field:SerializedName("Reviews")
    val reviews: String
)