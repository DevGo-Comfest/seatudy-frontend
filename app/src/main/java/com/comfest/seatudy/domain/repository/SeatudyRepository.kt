package com.comfest.seatudy.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.network.ApiService
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

    fun getCourseCategory(category: String): LiveData<Resource<Response<ResponseCoursesListDetailCategory>>> =
        liveData {
            emit(Resource.Loading())
            try {
                val apiClient = apiService.getCourseCategory(category)
                if (apiClient.isSuccessful) {
                    emit(Resource.Success(apiClient))
                } else {
                    emit(Resource.Error(apiClient.message()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }

    fun getSearchCourseName(category: String): LiveData<Resource<Response<ResponseCoursesListDetailCategory>>> =
        liveData {
            emit(Resource.Loading())
            try {
                val apiClient = apiService.getSearchCourseName(category)
                if (apiClient.isSuccessful) {
                    emit(Resource.Success(apiClient))
                } else {
                    emit(Resource.Error(apiClient.message()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }

    fun getCoursesWithID(id: String): LiveData<Resource<Response<ResponseCoursesListDetail>>> =
        liveData {
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

    fun getEnrolledCourse(token: String): LiveData<Resource<Response<ResponseEnrollCourse>>> =
        liveData {
            emit(Resource.Loading())
            try {
                val apiClient = apiService.getEnrolledCourse(token)
                if (apiClient.isSuccessful) {
                    emit(Resource.Success(apiClient))
                } else {
                    emit(Resource.Error(apiClient.message()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }

    fun buyCourse(
        dataBuy: DataBuy,
        token: String
    ): LiveData<Resource<Response<ResponseEnrollments>>> = liveData {
        emit(Resource.Loading())
        try {
            val apiClient = apiService.buyCourse(dataBuy, token)
            if (apiClient.isSuccessful) {
                emit(Resource.Success(apiClient))
            } else {
                emit(Resource.Error(apiClient.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun getForumID(id: String, token: String): LiveData<Resource<Response<ResponseForums>>> =
        liveData {
            emit(Resource.Loading())
            try {
                val apiClient = apiService.getForumID(id, token)
                if (apiClient.isSuccessful) {
                    emit(Resource.Success(apiClient))
                } else {
                    emit(Resource.Error(apiClient.message()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }

    fun sendPostForum(dataForum: DataForum, token: String): LiveData<Resource<Response<ResponseSendForums>>> =
        liveData {
            emit(Resource.Loading())
            try {
                val apiClient = apiService.sendPostForum(dataForum, token)
                if (apiClient.isSuccessful) {
                    emit(Resource.Success(apiClient))
                } else {
                    emit(Resource.Error(apiClient.message()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }


    fun getSearchCategoryLevelRating(
        category: String,
        level: String,
        rating: String
    ): LiveData<Resource<Response<ResponseCoursesListDetailCategory>>> = liveData {
        emit(Resource.Loading())
        try {
            val apiClient = apiService.getSearchCategoryLevelRating(category, level, rating)
            if (apiClient.isSuccessful) {
                emit(Resource.Success(apiClient))
            } else {
                emit(Resource.Error(apiClient.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun getSyllabus(id: String): LiveData<Resource<Response<ResponseSyllabus>>> =
        liveData {
            emit(Resource.Loading())
            try {
                val apiClient = apiService.getSyllabus(id)
                if (apiClient.isSuccessful) {
                    emit(Resource.Success(apiClient))
                } else {
                    emit(Resource.Error(apiClient.message()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }

    fun sendOpenAssignment(dataAssignment: DataAssignment, authToken: String): LiveData<Resource<Response<ResponseOpenAssignment>>> =
    liveData {
        emit(Resource.Loading())
        try {
            val apiClient = apiService.sendOpenAssignment(dataAssignment, authToken)
            if (apiClient.isSuccessful) {
                emit(Resource.Success(apiClient))
            } else {
                emit(Resource.Error(apiClient.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun sendAssignment(id: String, dataUrl: DataUrlAssignment, token: String): LiveData<Resource<Response<ResponseSubmissions>>> =
        liveData {
            emit(Resource.Loading())
            try {
                val apiClient = apiService.sendAssignment(id, dataUrl, token)
                if (apiClient.isSuccessful) {
                    emit(Resource.Success(apiClient))
                } else {
                    emit(Resource.Error(apiClient.message()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }

    fun getUserAssignment(id: String, token: String): LiveData<Resource<Response<ResponseUserAssignment>>> =
        liveData {
            emit(Resource.Loading())
            try {
                val apiClient = apiService.getUserAssignment(id, token)
                if (apiClient.isSuccessful) {
                    emit(Resource.Success(apiClient))
                } else {
                    emit(Resource.Error(apiClient.message()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }

    fun getProgress(id: String, token: String): LiveData<Resource<Response<ResponseProgress>>> =
        liveData {
            emit(Resource.Loading())
            try {
                val apiClient = apiService.getProgress(id, token)
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