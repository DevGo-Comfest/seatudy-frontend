package com.comfest.instructor.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CreateSyllabusMaterialResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("syllabus_material")
    val syllabusMaterial: SyllabusMaterial
)



@Parcelize
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
): Parcelable
