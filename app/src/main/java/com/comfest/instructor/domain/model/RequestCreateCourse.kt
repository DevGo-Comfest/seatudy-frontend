package com.comfest.instructor.domain.model

import com.google.gson.annotations.SerializedName

data class RequestCreateCourse(
    val title: String,
    val description: String,
    val price: Int,
    val category: String,
    val image_url: String,

    @field:SerializedName("difficulty_level")
    val difficultyLevel: String,
)
