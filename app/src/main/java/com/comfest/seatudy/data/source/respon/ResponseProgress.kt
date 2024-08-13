package com.comfest.seatudy.data.source.respon

import com.google.gson.annotations.SerializedName

data class ResponseProgress(
    @field:SerializedName("progress")
    val progress: String
)
