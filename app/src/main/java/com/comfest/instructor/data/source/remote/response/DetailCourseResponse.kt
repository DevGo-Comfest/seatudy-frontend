package com.comfest.instructor.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class DetailCourseResponse(
    @SerializedName("course")
    val course: CourseDetail
)

data class CourseDetail(
    @SerializedName("CourseID")
    val courseID: Int,

    @SerializedName("PrimaryAuthor")
    val primaryAuthor: String,

    @SerializedName("Title")
    val title: String,

    @SerializedName("Description")
    val description: String,

    @SerializedName("Price")
    val price: Int,

    @SerializedName("Category")
    val category: String,

    @SerializedName("ImageURL")
    val imageURL: String,

    @SerializedName("DifficultyLevel")
    val difficultyLevel: String,

    @SerializedName("CreatedDate")
    val createdDate: String,

    @SerializedName("UpdatedAt")
    val updatedAt: String,

    @SerializedName("Rating")
    val rating: Int,

    @SerializedName("Status")
    val status: String,

    @SerializedName("IsDeleted")
    val isDeleted: Boolean,

    @SerializedName("Syllabuses")
    val syllabuses: List<SyllabusDetail>,

    @SerializedName("Enrollments")
    val enrollments: Any?, // Adjust the type if needed

    @SerializedName("Progresses")
    val progresses: Any?, // Adjust the type if needed

    @SerializedName("ForumPosts")
    val forumPosts: Any?, // Adjust the type if needed

    @SerializedName("Reviews")
    val reviews: Any? // Adjust the type if needed
)

@Parcelize
data class SyllabusDetail(
    @SerializedName("SyllabusID")
    val syllabusID: Int,

    @SerializedName("Order")
    val order: Int,

    @SerializedName("Title")
    val title: String,

    @SerializedName("Description")
    val description: String,

    @SerializedName("InstructorID")
    val instructorID: String,

    @SerializedName("CourseID")
    val courseID: Int,

    @SerializedName("Materials")
    val materials: @RawValue Any?, // Adjust the type if needed

    @SerializedName("Assignments")
    val assignments: @RawValue Any? // Adjust the type if needed
): Parcelable
