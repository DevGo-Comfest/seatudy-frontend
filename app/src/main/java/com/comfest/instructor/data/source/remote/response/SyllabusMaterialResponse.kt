package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SyllabusMaterialResponse(
    @SerializedName("syllabus_material")
    val syllabusMaterial: SyllabusMaterial
)
