package com.comfest.instructor.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SyllabusMaterialResponse(
    @SerializedName("syllabus_materials")
    val syllabusMaterial: List<DataSyllabusMaterialResponse>
)


@Parcelize
data class DataSyllabusMaterialResponse(
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
