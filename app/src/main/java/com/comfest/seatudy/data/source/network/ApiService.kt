package com.comfest.seatudy.data.source.network

import com.comfest.seatudy.data.source.respon.ResponseCoursesList
import com.comfest.seatudy.data.source.respon.ResponseLogin
import com.comfest.seatudy.data.source.respon.ResponseProfile
import com.comfest.seatudy.data.source.respon.ResponseRegister
import com.comfest.seatudy.data.source.respon.ResponseTopUp
import com.comfest.seatudy.domain.model.DataLogin
import com.comfest.seatudy.domain.model.DataRegister
import com.comfest.seatudy.domain.model.DataTopUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("api/login")
    suspend fun loginUser(@Body dataLogin: DataLogin): ResponseLogin

    @POST("api/register")
    suspend fun registerUser(@Body dataRegister: DataRegister): ResponseRegister

    @GET("api/courses")
    suspend fun getCourses(): Response<ResponseCoursesList>

    @GET("api/courses/{id}")
    suspend fun getCoursesID(
        @Path("id") id: String
    ): Response<ResponseCoursesList>

    @GET("api/profile")
    suspend fun getProfile(@Header("Authorization") authToken: String): Response<ResponseProfile>

    @POST("api/profile")
    suspend fun topUp(@Body dataTopUp: DataTopUp, @Header("Authorization") authToken: String): ResponseTopUp
}