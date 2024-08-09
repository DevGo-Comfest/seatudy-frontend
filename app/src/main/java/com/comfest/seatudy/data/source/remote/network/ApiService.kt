package com.comfest.seatudy.data.source.remote.network

import com.comfest.seatudy.data.source.remote.respon.ResponseLogin
import com.comfest.seatudy.data.source.remote.respon.ResponseRegister
import com.comfest.seatudy.domain.model.DataLogin
import com.comfest.seatudy.domain.model.DataRegister
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun loginUser(@Body dataLogin: DataLogin): ResponseLogin

    @POST("api/register")
    suspend fun registerUser(@Body dataRegister: DataRegister): ResponseRegister

}