package com.comfest.instructor.domain.model

import com.google.gson.annotations.SerializedName

data class RequestCreateAssignment(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("maximum_time")
    val maximumTime: Int,
)
