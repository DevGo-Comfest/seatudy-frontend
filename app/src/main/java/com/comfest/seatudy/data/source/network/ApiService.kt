package com.comfest.seatudy.data.source.network

import com.comfest.seatudy.data.source.respon.ResponseCoursesList
import com.comfest.seatudy.data.source.respon.ResponseCoursesListDetail
import com.comfest.seatudy.data.source.respon.ResponseCoursesListDetailCategory
import com.comfest.seatudy.data.source.respon.ResponseEnrollCourse
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
import retrofit2.http.Query

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
    ): Response<ResponseCoursesListDetail>

    @GET("api/courses/search")
    suspend fun getCourseCategory(
        @Query("category") category: String
    ): Response<ResponseCoursesListDetailCategory>

    @GET("api/courses/search")
    suspend fun getSearchCourseName(
        @Query("q") nameCourse: String
    ): Response<ResponseCoursesListDetailCategory>

    @GET("api/enrolled-courses")
    suspend fun getEnrolledCourse(
        @Header("Authorization") authToken: String
    ): Response<ResponseEnrollCourse>




    @GET("api/profile")
    suspend fun getProfile(@Header("Authorization") authToken: String): Response<ResponseProfile>

    @POST("api/topup")
    suspend fun topUp(
        @Body dataTopUp: DataTopUp,
        @Header("Authorization") authToken: String
    ): ResponseTopUp
}