package com.comfest.seatudy.data.source.respon

import com.google.gson.annotations.SerializedName

data class ResponseForums(
    @field:SerializedName("forum_posts")
    val forumPosts: List<ResponseForumDetail>
)

data class ResponseForumDetail(
    @field:SerializedName("forum_post_id")
    val forumPostId: Int,

    @field:SerializedName("course_id")
    val courseId: Int,

    @field:SerializedName("content")
    val content: String,

    @field:SerializedName("user_id")
    val userId: String,

    @field:SerializedName("user_name")
    val userName: String,

    @field:SerializedName("user_role")
    val userRole: String,

    @field:SerializedName("date_posted")
    val datePosted: String
)

data class ResponseSendForums(
    @field:SerializedName("forum_post")
    val forumPosts: List<ResponseSendForumDetail>,

    @field:SerializedName("message")
    val message: String
)

data class ResponseSendForumDetail(
    @field:SerializedName("forum_post_id")
    val forumPostId: Int,

    @field:SerializedName("course_id")
    val courseId: Int,

    @field:SerializedName("user_id")
    val userId: String,

    @field:SerializedName("content")
    val content: String,

    @field:SerializedName("date_posted")
    val datePosted: String

)