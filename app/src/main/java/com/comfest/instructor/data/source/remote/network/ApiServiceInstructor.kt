package com.comfest.instructor.data.source.remote.network

import com.comfest.instructor.data.source.remote.response.CreateCourseResponse
import com.comfest.instructor.data.source.remote.response.UploadImageResponse
import com.comfest.instructor.domain.model.RequestCreateCourse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServiceInstructor {
    //upload image
    @Multipart
    @POST("api/courses/upload/image")
    suspend fun uploadImageCourse(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part
    ): UploadImageResponse


    @POST("api/courses")
    suspend fun createCourse(
        @Header("Authorization") token: String,
        @Body requestCreateCourse: RequestCreateCourse
    ): CreateCourseResponse
}