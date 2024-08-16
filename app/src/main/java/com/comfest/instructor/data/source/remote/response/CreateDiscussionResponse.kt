package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreateDiscussionResponse(
    @SerializedName("forum_post")
    val forumPost: ForumPost,

    @SerializedName("message")
    val message: String
)


data class ForumPost(
    @SerializedName("ForumPostID")
    val forumPostID: Int,

    @SerializedName("CourseID")
    val courseID: Int,

    @SerializedName("UserID")
    val userID: String,

    @SerializedName("Content")
    val content: String,

    @SerializedName("DatePosted")
    val datePosted: String
)
