package com.comfest.instructor.domain.model

import com.google.gson.annotations.SerializedName

data class RequestAssignInstructor(
    @SerializedName("instructor_data")
    val instructorData: List<String>
)
