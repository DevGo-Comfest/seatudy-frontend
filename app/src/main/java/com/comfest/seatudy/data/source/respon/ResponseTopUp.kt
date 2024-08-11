package com.comfest.seatudy.data.source.respon

import com.google.gson.annotations.SerializedName

data class ResponseTopUp(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("topup")
    val topUp: TopUP
)

data class TopUP(
    @field:SerializedName("TopupID")
    val topUpID: Int,

    @field:SerializedName("UserID")
    val userID: String,

    @field:SerializedName("Amount")
    val amount: Int,

    @field:SerializedName("Status")
    val status: String,

    @field:SerializedName("PaymentMethod")
    val paymentMethod: String,

    @field:SerializedName("CreatedDate")
    val createdDate: String
)
