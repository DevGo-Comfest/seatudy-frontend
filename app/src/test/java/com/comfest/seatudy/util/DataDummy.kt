package com.comfest.seatudy.util

import com.comfest.seatudy.data.source.respon.ResponseAssignments
import com.comfest.seatudy.data.source.respon.ResponseCourses
import com.comfest.seatudy.data.source.respon.ResponseCoursesDetail
import com.comfest.seatudy.data.source.respon.ResponseCoursesList
import com.comfest.seatudy.data.source.respon.ResponseCoursesListDetail
import com.comfest.seatudy.data.source.respon.ResponseCoursesListDetailCategory
import com.comfest.seatudy.data.source.respon.ResponseEnrollCourse
import com.comfest.seatudy.data.source.respon.ResponseEnrolledCourse
import com.comfest.seatudy.data.source.respon.ResponseEnrollments
import com.comfest.seatudy.data.source.respon.ResponseEnrollmentsDetail
import com.comfest.seatudy.data.source.respon.ResponseForumDetail
import com.comfest.seatudy.data.source.respon.ResponseForums
import com.comfest.seatudy.data.source.respon.ResponseLogin
import com.comfest.seatudy.data.source.respon.ResponseMaterials
import com.comfest.seatudy.data.source.respon.ResponseOpenAssignment
import com.comfest.seatudy.data.source.respon.ResponseProfile
import com.comfest.seatudy.data.source.respon.ResponseProgress
import com.comfest.seatudy.data.source.respon.ResponseRegister
import com.comfest.seatudy.data.source.respon.ResponseSendForumDetail
import com.comfest.seatudy.data.source.respon.ResponseSendForums
import com.comfest.seatudy.data.source.respon.ResponseSubmissionDetail
import com.comfest.seatudy.data.source.respon.ResponseSubmissions
import com.comfest.seatudy.data.source.respon.ResponseSyllabus
import com.comfest.seatudy.data.source.respon.ResponseSyllabuses
import com.comfest.seatudy.data.source.respon.ResponseTopUp
import com.comfest.seatudy.data.source.respon.ResponseUserAssignment
import com.comfest.seatudy.data.source.respon.ResponseUserLogin
import com.comfest.seatudy.data.source.respon.ResponseUserRegister
import com.comfest.seatudy.data.source.respon.TopUP
import com.comfest.seatudy.domain.model.DataAssignment
import com.comfest.seatudy.domain.model.DataBuy
import com.comfest.seatudy.domain.model.DataForum
import com.comfest.seatudy.domain.model.DataLogin
import com.comfest.seatudy.domain.model.DataRegister
import com.comfest.seatudy.domain.model.DataTopUp
import com.comfest.seatudy.domain.model.DataUrlAssignment

object DataDummy {

    fun generateLogin(): DataLogin {
        return (
                DataLogin(
                    "dewatriwijaya@gmail.com",
                    "1234567",
                ))
    }

    fun generateLoginResponse(): ResponseLogin {
        val user = ResponseUserLogin(
            "user-zOqgGlT3MzAY295Y",
            "dewatriwijaya",
            "dewa",
            "user"
        )
        return ResponseLogin(
            "success",
            "",
            user
        )
    }

    fun generateRegister(): DataRegister {
        return DataRegister(
            "dewa",
            "1234567",
            "12345678"
        )
    }

    fun generateRegisterResponse(): ResponseRegister =
        ResponseRegister(
            "false",
            ResponseUserRegister("", "", "", "", 0, "", "", "", 0, "", "", "", "", "")
        )

    fun generateProfileResponse(): ResponseProfile {
        return ResponseProfile(
            1000,
            "",
            "",
            "",
            "",
            "",
            ""
        )
    }

    fun generateBuyResponse(): ResponseEnrollments {
        val enroll = ResponseEnrollmentsDetail(
            1,
            "",
            0,
            ""
        )
        return ResponseEnrollments(
            enroll,
            ""
        )
    }

    fun generateBuy(): DataBuy {
        return DataBuy(
            1,
        )
    }

    fun generatePerSyllabusResponse(): ResponseUserAssignment {
        return ResponseUserAssignment(
            2,
            1,
            "",
            "",
            ""
        )
    }

    fun generateOpenAssignment(): DataAssignment {
        return DataAssignment(
            2
        )
    }

    fun generateOpenAssignmentResponse(): ResponseOpenAssignment{
        return ResponseOpenAssignment(
            ""
        )
    }

    fun generateGetSyllabusResponse(): ResponseSyllabus{
        val material = ResponseMaterials(
            0,
            0,
            "",
            "",
            "",
            "",
            0
        )
        val assignment = ResponseAssignments(
            0,
            0,
            "",
            "",
            0,
            "",
            ""
        )

        val syllabus = ResponseSyllabuses(
            0,
            0,
            "",
            "",
            "",
            0,
            false,
            listOf(material),
            listOf(assignment)
        )

        return ResponseSyllabus(
            syllabus
        )
    }

    fun generateCourseListDetailResponse(): ResponseCoursesListDetail{
        val material = ResponseMaterials(
            0,
            0,
            "",
            "",
            "",
            "",
            0
        )
        val assignment = ResponseAssignments(
            0,
            0,
            "",
            "",
            0,
            "",
            ""
        )
        val syllabus = ResponseSyllabuses(
            0,
            0,
            "",
            "",
            "",
            0,
            false,
            listOf(material),
            listOf(assignment)
        )
        val course = ResponseCoursesDetail(
            0,
            "",
            "",
            "",
            "",
            0,
            "",
            "",
            "",
            "",
            "",
            0,
            "",
            true,
            listOf(syllabus),
            "",
            "",
            "",
            ""
        )
        return ResponseCoursesListDetail(
            course
        )
    }

    fun generateCourseResponse(): ResponseCoursesList{
        val course = ResponseCourses(
            0,
            "",
            "",
            0,
            "",
            "",
            "",
            "",
            "",
            0,
            "",
            "",
            "",
            "",
            "",
            "",
        )
        return ResponseCoursesList(
            listOf(course)
        )
    }

    fun generateEnrollCourseResponse(): ResponseEnrollCourse{
        val material = ResponseMaterials(
            0,
            0,
            "",
            "",
            "",
            "",
            0
        )
        val assignment = ResponseAssignments(
            0,
            0,
            "",
            "",
            0,
            "",
            ""
        )

        val syllabus = ResponseSyllabuses(
            0,
            0,
            "",
            "",
            "",
            0,
            false,
            listOf(material),
            listOf(assignment)
        )
        val enrollCourse = ResponseEnrolledCourse(
            0,
            "",
            "",
            "",
            0,
            "",
            "",
            "",
            "",
            "",
            0,
            "",
            true,
            listOf(syllabus),
            "",
            "",
            "",
            ""
        )
        return ResponseEnrollCourse(
            listOf(enrollCourse)
        )
    }

    fun generateForumsResponse(): ResponseForums{
        val forumDetail = ResponseForumDetail(
            0,
            0,
            "",
            "",
            "",
            "",
            ""
        )
        return ResponseForums(
            listOf(forumDetail)
        )
    }

    fun generateSendForumsResponse(): ResponseSendForums{
        val forumDetail = ResponseSendForumDetail(
            0,
            0,
            "",
            "",
            ""
        )
        return ResponseSendForums(
            listOf(forumDetail),
            ""
        )
    }

    fun generateForum(): DataForum{
        return DataForum(
            0,
            ""
        )
    }

    fun generateProgressCourseResponse(): ResponseProgress{
        return ResponseProgress(
            34
        )
    }

    fun generateSendAssignmentResponse(): ResponseSubmissions{
        val submissionDetail = ResponseSubmissionDetail(
            0,
            "",
            0,
            "",
            true,
            0,
            "",
            "",
            ""
        )
        return ResponseSubmissions(
            "",
            submissionDetail
        )
    }

    fun generateSendAssignment(): DataUrlAssignment{
        return DataUrlAssignment(
            ""
        )
    }

    fun generateCourseCategoryResponse(): ResponseCoursesListDetailCategory {
        val material = ResponseMaterials(
            0,
            0,
            "",
            "",
            "",
            "",
            0
        )
        val assignment = ResponseAssignments(
            0,
            0,
            "",
            "",
            0,
            "",
            ""
        )
        val syllabus = ResponseSyllabuses(
            0,
            0,
            "",
            "",
            "",
            0,
            false,
            listOf(material),
            listOf(assignment)
        )
        val course = ResponseCoursesDetail(
            0,
            "",
            "",
            "",
            "",
            0,
            "",
            "",
            "",
            "",
            "",
            0,
            "",
            true,
            listOf(syllabus),
            "",
            "",
            "",
            ""
        )
        return ResponseCoursesListDetailCategory(
            listOf(course)
        )
    }

    fun generateTopUpResponse(): ResponseTopUp{
        val topUp = TopUP(
            12,
            "",
            200,
            "",
            "",
            ""
        )
        return ResponseTopUp(
            "",
            topUp
        )
    }

    fun generateTopUp(): DataTopUp {
        return DataTopUp(
            100,
            "Alfamart"
        )
    }
}

