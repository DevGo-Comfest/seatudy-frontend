package com.comfest.seatudy.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.respon.ResponseCoursesListDetail
import com.comfest.seatudy.data.source.respon.ResponseEnrollCourse
import com.comfest.seatudy.data.source.respon.ResponseForums
import com.comfest.seatudy.data.source.respon.ResponseSendForums
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailViewModel
import com.comfest.seatudy.util.DataDummy.generateCourseListDetailResponse
import com.comfest.seatudy.util.DataDummy.generateEnrollCourseResponse
import com.comfest.seatudy.util.DataDummy.generateForum
import com.comfest.seatudy.util.DataDummy.generateForumsResponse
import com.comfest.seatudy.util.DataDummy.generateSendForumsResponse
import com.comfest.seatudy.util.MainDispatcherRule
import com.comfest.seatudy.util.getOrAwaitValue
import com.comfest.seatudy.utils.SettingsPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CourseDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var courseDetail: CourseDetailViewModel

    @Mock
    private lateinit var seatudyRepository: SeatudyRepository

    @Mock
    private lateinit var pref: SettingsPreferences

    private val dummyCourseListDetailResponse = generateCourseListDetailResponse()
    private val dummyEnrollCourseResponse = generateEnrollCourseResponse()
    private val dummyForumsResponse = generateForumsResponse()
    private val dummySendForumsResponse = generateSendForumsResponse()
    private val dummySendForums = generateForum()


    @Before
    fun setUp() {
        courseDetail = CourseDetailViewModel(pref, seatudyRepository)
    }

    @Test
    fun `when Get Course With ID Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseCoursesListDetail>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyCourseListDetailResponse))
        Mockito.`when`(seatudyRepository.getCoursesWithID("")).thenReturn(expectedResponse)

        val actualResponse = courseDetail.getCoursesWithID("").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Get Enrolled Course Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseEnrollCourse>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyEnrollCourseResponse))
        Mockito.`when`(seatudyRepository.getEnrolledCourse("")).thenReturn(expectedResponse)

        val actualResponse = courseDetail.getEnrolledCourse("").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Get Forum With ID Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseForums>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyForumsResponse))
        Mockito.`when`(seatudyRepository.getForumID("2", "hallo")).thenReturn(expectedResponse)

        val actualResponse = courseDetail.getForumID("2","hallo").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Send Post Forum Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseSendForums>>>()
        expectedResponse.value = Resource.Success(Response.success(dummySendForumsResponse))
        Mockito.`when`(seatudyRepository.sendPostForum(dummySendForums, "")).thenReturn(expectedResponse)

        val actualResponse = courseDetail.sendPostForum(dummySendForums, "").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

}