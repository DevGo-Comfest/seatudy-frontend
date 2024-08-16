package com.comfest.instructor.domain.model

import com.google.gson.annotations.SerializedName

data class RequestAddGradeSubmission(
    @SerializedName("grade")
    val gradeScore: Int,
)
