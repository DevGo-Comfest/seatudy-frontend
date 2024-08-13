package com.comfest.seatudy.data.source.respon

import com.google.gson.annotations.SerializedName

data class ResponseMaterials(
    @field:SerializedName("SyllabusMaterialID")
    val syllabusMaterialID: Int,

    @field:SerializedName("Order")
    val order: Int,

    @field:SerializedName("Title")
    val title: String,

    @field:SerializedName("Description")
    val description: String,

    @field:SerializedName("URLMaterial")
    val urlMaterial: String,

    @field:SerializedName("TimeNeeded")
    val timeNeeded: String,

    @field:SerializedName("SyllabusID")
    val syllabusID: Int
)
