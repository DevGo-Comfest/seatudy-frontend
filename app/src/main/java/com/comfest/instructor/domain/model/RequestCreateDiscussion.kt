package com.comfest.instructor.domain.model

import com.google.gson.annotations.SerializedName

data class RequestCreateDiscussion(
    @SerializedName("course_id")
    val courseId: Int,

    @SerializedName("content")
    val messageDiscussion: String
)
