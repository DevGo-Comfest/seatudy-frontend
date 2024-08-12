package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreateSyllabusResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("syllabus")
    val syllabus: Syllabus
)

data class Syllabus(
    @SerializedName("SyllabusID")
    val SyllabusID: Int,

    @SerializedName("Order")
    val Order: Int,

    @SerializedName("Title")
    val Title: String,

    @SerializedName("Description")
    val Description: String,

    @SerializedName("InstructorID")
    val InstructorID: String,

    @SerializedName("CourseID")
    val CourseID: Int,

    @SerializedName("Materials")
    val Materials: Any?,

    @SerializedName("Assignments")
    val Assignments: Any?
)
