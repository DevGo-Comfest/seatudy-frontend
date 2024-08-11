package com.comfest.seatudy.domain.model

import com.google.gson.annotations.SerializedName

data class DataCourseList(
    val nameCourse: String,
    val rating: String,
    val hour: String,
    val levelCourse: String,
    val category: String,
    val progress: String,
    val description: String,
    val price: String,
    val imageURL: String,
    val createdDate: String,
    val updatedAt: String,
    val status: String,
    val syllabuses: List<DataSyllabus>,
    val enrollments: String,
    //val forumPosts:,
    //val reviews:
)

data class DataSyllabus(
    val title: String,
    val description: String,
    val linkYoutube: String,
)

data class DataForum(
    val name: String,
    val rating: String
)
