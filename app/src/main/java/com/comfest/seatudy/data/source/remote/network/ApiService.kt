package com.comfest.seatudy.data.source.remote.network

import com.comfest.seatudy.data.source.remote.respon.ResponseCoursesList
import com.comfest.seatudy.data.source.remote.respon.ResponseLogin
import com.comfest.seatudy.data.source.remote.respon.ResponseRegister
import com.comfest.seatudy.domain.model.DataLogin
import com.comfest.seatudy.domain.model.DataRegister
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("api/login")
    suspend fun loginUser(@Body dataLogin: DataLogin): ResponseLogin

    @POST("api/register")
    suspend fun registerUser(@Body dataRegister: DataRegister): ResponseRegister

    @GET("api/courses")
    suspend fun getCourses(): ResponseCoursesList

    @GET("api/courses/{id}")
    suspend fun getCourses(
        @Path("id") id: String
    ): ResponseCoursesList
}