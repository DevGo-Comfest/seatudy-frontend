package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreateSyllabusMaterialResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("syllabus_material")
    val syllabusMaterial: SyllabusMaterial
)


data class SyllabusMaterial(
    @SerializedName("SyllabusMaterialID")
    val syllabusMaterialID: Int,

    @SerializedName("Order")
    val order: Int,

    @SerializedName("Title")
    val title: String,

    @SerializedName("Description")
    val description: String,

    @SerializedName("URLMaterial")
    val urlMaterial: String,

    @SerializedName("TimeNeeded")
    val timeNeeded: String,

    @SerializedName("SyllabusID")
    val syllabusID: Int
)
