package com.comfest.seatudy.data.source.network

import com.comfest.seatudy.data.source.respon.ResponseCoursesList
import com.comfest.seatudy.data.source.respon.ResponseCoursesListDetail
import com.comfest.seatudy.data.source.respon.ResponseCoursesListDetailCategory
import com.comfest.seatudy.data.source.respon.ResponseEnrollCourse
import com.comfest.seatudy.data.source.respon.ResponseEnrollments
import com.comfest.seatudy.data.source.respon.ResponseForums
import com.comfest.seatudy.data.source.respon.ResponseLogin
import com.comfest.seatudy.data.source.respon.ResponseOpenAssignment
import com.comfest.seatudy.data.source.respon.ResponseProfile
import com.comfest.seatudy.data.source.respon.ResponseProgress
import com.comfest.seatudy.data.source.respon.ResponseRegister
import com.comfest.seatudy.data.source.respon.ResponseSendForums
import com.comfest.seatudy.data.source.respon.ResponseSubmissions
import com.comfest.seatudy.data.source.respon.ResponseSyllabus
import com.comfest.seatudy.data.source.respon.ResponseTopUp
import com.comfest.seatudy.data.source.respon.ResponseUserAssignment
import com.comfest.seatudy.domain.model.DataAssignment
import com.comfest.seatudy.domain.model.DataBuy
import com.comfest.seatudy.domain.model.DataForum
import com.comfest.seatudy.domain.model.DataLogin
import com.comfest.seatudy.domain.model.DataRegister
import com.comfest.seatudy.domain.model.DataTopUp
import com.comfest.seatudy.domain.model.DataUrlAssignment
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

    @POST("api/enroll")
    suspend fun buyCourse(
        @Body dataBuy: DataBuy,
        @Header("Authorization") authToken: String
    ): Response<ResponseEnrollments>


    @GET("api/progress/course/{id}")
    suspend fun getProgress(
        @Path("id") id: String,
        @Header("Authorization") authToken: String
    ): Response<ResponseProgress>

    @GET("api/courses/search")
    suspend fun getSearchCategoryLevelRating(
        @Query("category") category: String,
        @Query("difficulty_level") level: String,
        @Query("rating") rating: String
    ): Response<ResponseCoursesListDetailCategory>

    @GET("api/syllabus/{id}")
    suspend fun getSyllabus(
        @Path("id") id: String
    ): Response<ResponseSyllabus>

    @GET("api/forum-post/{id}")
    suspend fun getForumID(
        @Path("id") id: String,
        @Header("Authorization") authToken: String
    ): Response<ResponseForums>

    @POST("api/forum-post")
    suspend fun sendPostForum(
        @Body dataForum: DataForum,
        @Header("Authorization") authToken: String
    ): Response<ResponseSendForums>

    @POST("api/assignments/open")
    suspend fun sendOpenAssignment(
        @Body dataAssignment: DataAssignment,
        @Header("Authorization") authToken: String
    ): Response<ResponseOpenAssignment>

    @POST("api/assignments/{id}/submissions")
    suspend fun sendAssignment(
        @Path("id") id: String,
        @Body dataUrl: DataUrlAssignment,
        @Header("Authorization") authToken: String
    ): Response<ResponseSubmissions>

    @GET("api/user-assignment/{id}")
    suspend fun getUserAssignment(
        @Path("id") id: String,
        @Header("Authorization") authToken: String
    ): Response<ResponseUserAssignment>

    @GET("api/profile")
    suspend fun getProfile(@Header("Authorization") authToken: String): Response<ResponseProfile>

    @POST("api/topup")
    suspend fun topUp(
        @Body dataTopUp: DataTopUp,
        @Header("Authorization") authToken: String
    ): ResponseTopUp
}