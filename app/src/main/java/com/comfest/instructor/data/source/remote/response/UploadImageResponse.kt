package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UploadImageResponse(
    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("message")
    val message: String
)
