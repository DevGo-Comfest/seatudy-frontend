package com.comfest.seatudy.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.network.ApiService
import com.comfest.seatudy.data.source.respon.ResponseCourses
import com.comfest.seatudy.data.source.respon.ResponseCoursesList
import com.comfest.seatudy.data.source.respon.ResponseLogin
import com.comfest.seatudy.data.source.respon.ResponseProfile
import com.comfest.seatudy.data.source.respon.ResponseRegister
import com.comfest.seatudy.data.source.respon.ResponseTopUp
import com.comfest.seatudy.domain.model.DataLogin
import com.comfest.seatudy.domain.model.DataRegister
import com.comfest.seatudy.domain.model.DataTopUp
import retrofit2.Response
import javax.inject.Inject

class SeatudyRepository @Inject constructor(private val apiService: ApiService) {

    fun login(data: DataLogin): LiveData<Resource<ResponseLogin>> = liveData {
        emit(Resource.Loading())
        try {
            val apiClient = apiService.loginUser(data)
            if (apiClient.message == "Login successful") {
                emit(Resource.Success(apiClient))
            } else {
                emit(Resource.Error(apiClient.message))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun register(data: DataRegister): LiveData<Resource<ResponseRegister>> = liveData {
        emit(Resource.Loading())
        try {
            val apiClient = apiService.registerUser(data)
            if (apiClient.message == "User registered successfully") {
                emit(Resource.Success(apiClient))
            } else {
                emit(Resource.Error(apiClient.message))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun getCourses(): LiveData<Resource<Response<ResponseCoursesList>>> = liveData {
        emit(Resource.Loading())
        try {
            val apiClient = apiService.getCourses()
            if (apiClient.isSuccessful) {
                emit(Resource.Success(apiClient))
            } else {
                emit(Resource.Error(apiClient.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun getCoursesWithID(id: String): LiveData<Resource<Response<ResponseCoursesList>>> = liveData {
        emit(Resource.Loading())
        try {
            val apiClient = apiService.getCoursesID(id)
            if (apiClient.isSuccessful) {
                emit(Resource.Success(apiClient))
            } else {
                emit(Resource.Error(apiClient.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun getProfile(token: String): LiveData<Resource<Response<ResponseProfile>>> = liveData {
        emit(Resource.Loading())
        try {
            val apiClient = apiService.getProfile(token)
            if (apiClient.isSuccessful) {
                emit(Resource.Success(apiClient))
            } else {
                emit(Resource.Error(apiClient.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun topUp(dataTopUp: DataTopUp, token: String): LiveData<Resource<ResponseTopUp>> = liveData {
        emit(Resource.Loading())
        try {
            val apiClient = apiService.topUp(dataTopUp, token)
            if (apiClient.message == "Top-up successful") {
                emit(Resource.Success(apiClient))
            } else {
                emit(Resource.Error(apiClient.message))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

}