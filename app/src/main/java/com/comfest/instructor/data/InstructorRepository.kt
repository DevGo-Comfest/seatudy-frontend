package com.comfest.instructor.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.comfest.instructor.data.source.remote.network.ApiServiceInstructor
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.data.source.remote.response.CourseResponse
import com.comfest.instructor.data.source.remote.response.CreateAssignmentResponse
import com.comfest.instructor.data.source.remote.response.CreateCourseResponse
import com.comfest.instructor.data.source.remote.response.CreateSyllabusResponse
import com.comfest.instructor.data.source.remote.response.DetailCourseResponse
import com.comfest.instructor.data.source.remote.response.UploadImageResponse
import com.comfest.instructor.domain.model.RequestCreateAssignment
import com.comfest.instructor.domain.model.RequestCreateCourse
import com.comfest.instructor.domain.model.RequestCreateSyllabus
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


    fun createCourse(token: String, requestCreateCourse: RequestCreateCourse): LiveData<Resource<CreateCourseResponse>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.createCourse(token, requestCreateCourse)
            if (response.message == "Course created successfully") {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun getCourse(token: String): LiveData<Resource<Response<CourseResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.getCourse(token)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun updateCourse(id: Int, token: String, requestCreateCourse: RequestCreateCourse): LiveData<Resource<Response<CreateCourseResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.updateCourse(id, token, requestCreateCourse)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun activatedCourse(id: Int, token: String): LiveData<Resource<Response<CreateCourseResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.activatedCourse(id, token)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
            }
        }


    fun createSyllabus(token: String, requestCreateSyllabus: RequestCreateSyllabus): LiveData<Resource<Response<CreateSyllabusResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.createSyllabus(token, requestCreateSyllabus)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun createAssignment(id: Int, token: String, requestCreateAssignment: RequestCreateAssignment): LiveData<Resource<Response<CreateAssignmentResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.createAssignment(id, token, requestCreateAssignment)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun getDetailCourse(courseId: Int, token: String): LiveData<Resource<Response<DetailCourseResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.getDetailCourse(courseId, token)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


}