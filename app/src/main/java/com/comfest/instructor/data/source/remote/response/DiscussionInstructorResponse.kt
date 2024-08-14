package com.comfest.instructor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DiscussionInstructorResponse(
    @SerializedName("forum_posts")
    val forumPosts: List<Discussion>
)

data class Discussion(
    @SerializedName("forum_post_id")
    val forumPostId: Int,

    @SerializedName("course_id")
    val courseId: Int,

    @SerializedName("content")
    val content: String,

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("user_name")
    val userName: String,

    @SerializedName("user_role")
    val userRole: String,

    @SerializedName("date_posted")
    val datePosted: String
)
