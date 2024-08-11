package com.comfest.instructor.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.comfest.instructor.data.source.remote.network.ApiServiceInstructor
import com.comfest.instructor.data.source.remote.response.UploadImageResponse
import com.comfest.seatudy.data.Resource
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class InstructorRepository @Inject constructor(private val apiServiceInstructor: ApiServiceInstructor) {

    fun uploadImage(token: String, image: MultipartBody.Part): LiveData<Resource<UploadImageResponse>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.uploadImageCourse(token, image)
            if (response.message == "Image uploaded successfully") {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }
}