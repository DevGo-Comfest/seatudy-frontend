package com.comfest.seatudy.domain.model

data class DataCourseList(
    val nameCourse: String,
    val rating: String,
    val hour: String,
    val levelCourse: String,
    val category: String,
    val description: String,
    val price: Int,
    val imageURL: String,
    val createdDate: String,
    val updatedAt: String,
    val status: String,
    val syllabuses: List<DataSyllabus>,
    val enrollments: String,
    val forumPosts: List<DataForum>,
    val reviews: DataReview,
)

data class DataSyllabus(
    val title: String,
    val description: String,
    val linkYoutube: String,
)

data class DataForum(
    val course_id: Int,
    val content: String
)

data class DataReview(
    val nameInstructor: String,
    val description: String,
    val rating: String
)
