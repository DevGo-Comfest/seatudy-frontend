package com.comfest.instructor.domain.model

import com.google.gson.annotations.SerializedName

data class RequestUpdateSyllabus(

    @field: SerializedName("title")
    val title: String,

    @field: SerializedName("description")
    val description: String,
)
