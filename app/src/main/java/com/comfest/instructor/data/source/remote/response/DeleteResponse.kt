package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DeleteResponse(
    @SerializedName("message")
    val message: String
)
