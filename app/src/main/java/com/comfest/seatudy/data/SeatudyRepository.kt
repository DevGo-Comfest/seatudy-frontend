package com.comfest.seatudy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.comfest.seatudy.data.source.remote.network.ApiService
import com.comfest.seatudy.data.source.remote.respon.ResponseLogin
import com.comfest.seatudy.data.source.remote.respon.ResponseRegister
import com.comfest.seatudy.domain.model.DataLogin
import com.comfest.seatudy.domain.model.DataRegister
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
}