package com.comfest.seatudy.data.source.respon

import com.google.gson.annotations.SerializedName


data class ResponseEnrollments(
    @field:SerializedName("enrollment")
    val enrollment: ResponseEnrollmentsDetail,

    @field:SerializedName("message")
    val message: String
)

data class ResponseEnrollmentsDetail(
    @field:SerializedName("EnrollmentID")
    val enrollmentID: Int,

    @field:SerializedName("UserID")
    val userID: String,

    @field:SerializedName("CourseID")
    val courseID: Int,

    @field:SerializedName("DateEnrolled")
    val dateEnrolled: String
)