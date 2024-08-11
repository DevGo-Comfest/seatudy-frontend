package com.comfest.seatudy.dummy

import com.comfest.seatudy.domain.model.DataCourseList
import com.comfest.seatudy.domain.model.DataForum
import com.comfest.seatudy.domain.model.DataReview
import com.comfest.seatudy.domain.model.DataSyllabus

object DataDummy {
    val listDataCourse = listOf(
        DataCourseList(
            "Software Engineer Android Development",
            "5",
            "12",
            "Intermediate",
            "Android",
            "25",
            "Zoom is the leader in modern enterprise video communications, with an easy, reliable cloud platform for video and audio conferencing, chat, and webinars across mobile, desktop, and room systems.",
            "1.200.000",
            "https://i.ytimg.com/vi/5atDfBKg9JE/maxresdefault.jpg",
            "",
            "",
            "Activate",
            listOf(
                DataSyllabus(
                    "Whats in Android",
                    "Lorem",
                    ""
                ),
                DataSyllabus(
                    "Whats in Android",
                    "Lorem",
                    ""
                ),
                DataSyllabus(
                    "Whats in Android",
                    "Lorem",
                    "https://discord.com/channels/1254425469499998238/1269559910493065308/1272017821684990066"
                )
            ),
            "Activate",
            listOf(
                DataForum(
                    "Dewa",
                    "Lorem Ipsum Dallar Sit Amet"
                ),
                DataForum(
                    "Ibra",
                    "Lorem Ipsum Dallar Sit Amet"
                ),
                DataForum(
                    "Dimas",
                    "Lorem Ipsum Dallar Sit Amet"
                )
            ),
            DataReview(
                "Dewa Tri Wijaya",
                "Lorem Ipsum Dallar Sit Amet",
                "4"
            )
        ),
        DataCourseList(
            "Software Engineer Web Development",
            "3",
            "15",
            "Expert",
            "Web",
            "50",
            "Zoom is the leader in modern enterprise video communications, with an easy, reliable cloud platform for video and audio conferencing, chat, and webinars across mobile, desktop, and room systems.",
            "1.200.000",
            "https://i.ytimg.com/vi/5atDfBKg9JE/maxresdefault.jpg",
            "",
            "",
            "Activate",
            listOf(
                DataSyllabus(
                    "Whats in Android",
                    "Lorem",
                    "https:youtube.com"
                )
            ),
            "InActivate",
            listOf(
                DataForum(
                    "Dewa",
                    "Lorem Ipsum Dallar Sit Amet"
                ),
                DataForum(
                    "Ibra",
                    "Lorem Ipsum Dallar Sit Amet"
                ),
                DataForum(
                    "Dimas",
                    "Lorem Ipsum Dallar Sit Amet"
                )
            ),
            DataReview(
                "Dewa Tri Wijaya",
                "Lorem Ipsum Dallar Sit Amet",
                "4"
            )
        ),
        DataCourseList(
            "Software Engineer React Native",
            "2",
            "19",
            "Beginner",
            "Coding",
            "60",
            "Zoom is the leader in modern enterprise video communications, with an easy, reliable cloud platform for video and audio conferencing, chat, and webinars across mobile, desktop, and room systems.",
            "1.200.000",
            "https://i.ytimg.com/vi/5atDfBKg9JE/maxresdefault.jpg",
            "",
            "",
            "Activate",
            listOf(
                DataSyllabus(
                    "Whats in Android",
                    "Lorem",
                    "https:youtube.com"
                ),
                DataSyllabus(
                    "Whats in Android",
                    "Lorem",
                    "https:youtube.com"
                ),
                DataSyllabus(
                    "Whats in Android",
                    "Lorem",
                    "https:youtube.com"
                )
            ),
            "Activate",
            listOf(
                DataForum(
                    "Dewa",
                    "Lorem Ipsum Dallar Sit Amet"
                ),
                DataForum(
                    "Ibra",
                    "Lorem Ipsum Dallar Sit Amet"
                ),
                DataForum(
                    "Dimas",
                    "Lorem Ipsum Dallar Sit Amet"
                )
            ),
            DataReview(
                "Dewa Tri Wijaya",
                "Lorem Ipsum Dallar Sit Amet",
                "4"
            )
        )
    )
}