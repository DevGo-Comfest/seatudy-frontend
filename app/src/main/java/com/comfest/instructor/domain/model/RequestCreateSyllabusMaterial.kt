package com.comfest.instructor.domain.model

import com.google.gson.annotations.SerializedName

data class RequestCreateSyllabusMaterial(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url_material")
    val urlMaterial: String,

    @SerializedName("time_needed")
    val timeNeeded: String,

    @SerializedName("syllabus_id")
    val syllabusID: Int
)
