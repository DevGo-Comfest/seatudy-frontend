package com.comfest.instructor.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.comfest.instructor.data.source.remote.network.ApiServiceInstructor
import com.comfest.instructor.data.source.remote.response.AssignmentSyllabusResponse
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.data.source.remote.response.CourseResponse
import com.comfest.instructor.data.source.remote.response.CreateAssignmentResponse
import com.comfest.instructor.data.source.remote.response.CreateCourseResponse
import com.comfest.instructor.data.source.remote.response.CreateDiscussionResponse
import com.comfest.instructor.data.source.remote.response.CreateSyllabusMaterialResponse
import com.comfest.instructor.data.source.remote.response.CreateSyllabusResponse
import com.comfest.instructor.data.source.remote.response.DataSubmissionUserResponse
import com.comfest.instructor.data.source.remote.response.DeleteResponse
import com.comfest.instructor.data.source.remote.response.DetailCourseResponse
import com.comfest.instructor.data.source.remote.response.DiscussionInstructorResponse
import com.comfest.instructor.data.source.remote.response.InstructorResponse
import com.comfest.instructor.data.source.remote.response.SyllabusMaterialResponse
import com.comfest.instructor.data.source.remote.response.UpdateAssignmentResponse
import com.comfest.instructor.data.source.remote.response.UploadImageResponse
import com.comfest.instructor.domain.model.RequestAssignInstructor
import com.comfest.instructor.domain.model.RequestCreateAssignment
import com.comfest.instructor.domain.model.RequestCreateCourse
import com.comfest.instructor.domain.model.RequestCreateDiscussion
import com.comfest.instructor.domain.model.RequestCreateSyllabus
import com.comfest.instructor.domain.model.RequestCreateSyllabusMaterial
import com.comfest.instructor.domain.model.RequestUpdateSyllabus
import com.comfest.instructor.domain.model.RequestUpdateSyllabusMaterial
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


    fun getAssignmentBySyllabusId(syllabusId: Int, token: String): LiveData<Resource<Response<AssignmentSyllabusResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.getAssignmentBySyllabusId(syllabusId, token)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun updateSyllabus(syllabusId: Int, token: String, requestUpdateSyllabus: RequestUpdateSyllabus): LiveData<Resource<Response<CreateSyllabusResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.updateSyllabus(syllabusId, token, requestUpdateSyllabus)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun deleteSyllabus(syllabusId: Int, token: String): LiveData<Resource<Response<DeleteResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.deleteSyllabus(syllabusId, token)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun updateAssignment(assignmentId: Int, token: String, requestCreateAssignment: RequestCreateAssignment): LiveData<Resource<Response<UpdateAssignmentResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.updateAssignment(assignmentId, token, requestCreateAssignment)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun deleteAssignment(assignmentId: Int, token: String): LiveData<Resource<Response<DeleteResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.deleteAssignment(assignmentId, token)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun createSyllabusMaterial(token: String, requestCreateSyllabusMaterial: RequestCreateSyllabusMaterial): LiveData<Resource<Response<CreateSyllabusMaterialResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.createSyllabusMaterial(token, requestCreateSyllabusMaterial)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun getSyllabusMaterialById(syllabusId: Int, token: String): LiveData<Resource<Response<SyllabusMaterialResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.getSyllabusMaterialById(syllabusId, token)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun updateSyllabusMaterial(syllabusMaterialId: Int, token: String, requestUpdateSyllabusMaterial: RequestUpdateSyllabusMaterial): LiveData<Resource<Response<CreateSyllabusMaterialResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.updateSyllabusMaterial(syllabusMaterialId, token, requestUpdateSyllabusMaterial)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun deleteSyllabusMaterial(syllabusMaterialId: Int, token: String): LiveData<Resource<Response<DeleteResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.deleteSyllabusMaterial(syllabusMaterialId, token)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun getInstructor(): LiveData<Resource<Response<InstructorResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.getInstructor()
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun assignInstructor(courseId: Int, token: String, requestAssignInstructor: RequestAssignInstructor): LiveData<Resource<Response<ResponseBody>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.addAssignInstructor(courseId,token, requestAssignInstructor)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun getSubmissionUser(syllabusId: Int, token: String): LiveData<Resource<Response<DataSubmissionUserResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.getSubmissionUser(syllabusId,token)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun getDiscussion(courseId: Int, token: String): LiveData<Resource<Response<DiscussionInstructorResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.getDiscussion(courseId,token)
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }


    fun createMessageDiscussion(token: String, requestCreateDiscussion: RequestCreateDiscussion): LiveData<Resource<Response<CreateDiscussionResponse>>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiServiceInstructor.addMessageDiscussion(token, requestCreateDiscussion)
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