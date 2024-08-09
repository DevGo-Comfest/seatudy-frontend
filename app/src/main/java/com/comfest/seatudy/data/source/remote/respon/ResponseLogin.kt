package com.comfest.seatudy.data.source.remote.respon

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("token")
    val token: String,

    @field:SerializedName("user")
    val user: ResponseUserLogin
)

data class ResponseUserLogin(
    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("role")
    val role: String
)
