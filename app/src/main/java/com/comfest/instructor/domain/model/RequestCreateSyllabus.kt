package com.comfest.instructor.domain.model

import com.google.gson.annotations.SerializedName

data class RequestCreateSyllabus(
    @field: SerializedName("title")
    val title: String,

    @field: SerializedName("description")
    val description: String,

    @field: SerializedName("course_id")
    val courseId: Int,

)
