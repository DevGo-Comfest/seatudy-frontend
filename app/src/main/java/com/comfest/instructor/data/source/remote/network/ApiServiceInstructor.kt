package com.comfest.instructor.data.source.remote.network

import com.comfest.instructor.data.source.remote.response.AddGradeSubmissionResponse
import com.comfest.instructor.data.source.remote.response.AssignmentSyllabusResponse
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
import com.comfest.instructor.domain.model.RequestAddGradeSubmission
import com.comfest.instructor.domain.model.RequestAssignInstructor
import com.comfest.instructor.domain.model.RequestCreateAssignment
import com.comfest.instructor.domain.model.RequestCreateCourse
import com.comfest.instructor.domain.model.RequestCreateDiscussion
import com.comfest.instructor.domain.model.RequestCreateSyllabus
import com.comfest.instructor.domain.model.RequestCreateSyllabusMaterial
import com.comfest.instructor.domain.model.RequestUpdateSyllabus
import com.comfest.instructor.domain.model.RequestUpdateSyllabusMaterial
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

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


    @GET("api/courses/me")
    suspend fun getCourse(
        @Header("Authorization") token: String,
    ): Response<CourseResponse>


    @PUT("api/courses/{id}")
    suspend fun updateCourse(
        @Path("id") courseId: Int,
        @Header("Authorization") token: String,
        @Body requestCreateCourse: RequestCreateCourse
    ): Response<CreateCourseResponse>


    @PUT("api/courses/{id}/activate")
    suspend fun activatedCourse(
        @Path("id") courseId: Int,
        @Header("Authorization") token: String,
    ): Response<CreateCourseResponse>

    @POST("api/syllabus")
    suspend fun createSyllabus(
        @Header("Authorization") token: String,
        @Body requestCreateSyllabus: RequestCreateSyllabus
    ): Response<CreateSyllabusResponse>



    @POST("api/syllabus/{id}/assignments")
    suspend fun createAssignment (
        @Path("id") courseId: Int,
        @Header("Authorization") token: String,
        @Body requestCreateAssignment: RequestCreateAssignment
    ): Response<CreateAssignmentResponse>


    @GET("/api/courses/{id}")
    suspend fun getDetailCourse(
        @Path("id") courseId: Int,
        @Header("Authorization") token: String,
    ): Response<DetailCourseResponse>


    @GET("api/syllabus/{id}")
    suspend fun getAssignmentBySyllabusId(
        @Path("id") syllabusId: Int,
        @Header("Authorization") token: String,
    ): Response<AssignmentSyllabusResponse>



    @PUT("api/syllabus/{id}")
    suspend fun updateSyllabus(
        @Path("id") syllabusId: Int,
        @Header("Authorization") token: String,
        @Body requestCreateSyllabus: RequestUpdateSyllabus
    ): Response<CreateSyllabusResponse>


    @DELETE("api/syllabus/{id}")
    suspend fun deleteSyllabus(
        @Path("id") syllabusId: Int,
        @Header("Authorization") token: String,
    ): Response<DeleteResponse>


    @PUT("api/assignments/{id}")
    suspend fun updateAssignment(
        @Path("id") assignmentId: Int,
        @Header("Authorization") token: String,
        @Body requestCreateAssignment: RequestCreateAssignment
    ): Response<UpdateAssignmentResponse>


    @DELETE("api/assignments/{id}")
    suspend fun deleteAssignment(
        @Path("id") assignmentId: Int,
        @Header("Authorization") token: String,
    ): Response<DeleteResponse>


    @POST("api/syllabus-material")
    suspend fun createSyllabusMaterial (
        @Header("Authorization") token: String,
        @Body requestCreateSyllabusMaterial: RequestCreateSyllabusMaterial
    ): Response<CreateSyllabusMaterialResponse>


    @GET("api/syllabus-materials/{id}")
    suspend fun getSyllabusMaterialById(
        @Path("id") syllabusId: Int,
        @Header("Authorization") token: String,
    ): Response<SyllabusMaterialResponse>


    @PUT("api/syllabus-material/{id}")
    suspend fun updateSyllabusMaterial(
        @Path("id") syllabusMaterialId: Int,
        @Header("Authorization") token: String,
        @Body requestUpdateSyllabusMaterial: RequestUpdateSyllabusMaterial
    ): Response<CreateSyllabusMaterialResponse>

    @DELETE("api/syllabus-material/{id}")
    suspend fun deleteSyllabusMaterial(
        @Path("id") syllabusMaterialId: Int,
        @Header("Authorization") token: String,
    ): Response<DeleteResponse>


    @GET("api/instructors")
    suspend fun getInstructor(): Response<InstructorResponse>

    @POST("api/courses/{id}/instructors")
    suspend fun addAssignInstructor(
        @Path("id") courseId: Int,
        @Header("Authorization") token: String,
        @Body requestAssignInstructor: RequestAssignInstructor,
    ): Response<ResponseBody>


    //get submission user
    @GET("api/assignments/{id}")
    suspend fun getSubmissionUser(
        @Path("id") syllabusId: Int,
        @Header("Authorization") token: String,
    ): Response<DataSubmissionUserResponse>


    //get discussion
    @GET("api/forum-post/{id}")
    suspend fun getDiscussion(
        @Path("id") courseId: Int,
        @Header("Authorization") token: String,
    ): Response<DiscussionInstructorResponse>


    @POST("api/forum-post")
    suspend fun addMessageDiscussion(
        @Header("Authorization") token: String,
        @Body requestCreateDiscussion: RequestCreateDiscussion,
    ): Response<CreateDiscussionResponse>


    // give a grade submission
    @PUT("/api/submissions/{id}/grade")
    suspend fun addGradeSubmission(
        @Path("id") submissionId: Int,
        @Header("Authorization") token: String,
        @Body requestAddGradeSubmission: RequestAddGradeSubmission
    ): Response<AddGradeSubmissionResponse>

}