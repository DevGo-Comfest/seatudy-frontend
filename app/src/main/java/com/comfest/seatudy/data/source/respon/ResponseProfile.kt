package com.comfest.seatudy.data.source.respon

import com.google.gson.annotations.SerializedName

data class ResponseProfile(
    @field:SerializedName("balance")
    val balance: Int,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("role")
    val role: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String,

    @field:SerializedName("userId")
    val userId: String
)
