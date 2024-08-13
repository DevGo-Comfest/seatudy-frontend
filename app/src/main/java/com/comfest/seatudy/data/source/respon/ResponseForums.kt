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