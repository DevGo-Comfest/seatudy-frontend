package com.comfest.seatudy.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.respon.ResponseCoursesListDetail
import com.comfest.seatudy.data.source.respon.ResponseEnrollCourse
import com.comfest.seatudy.data.source.respon.ResponseProgress
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.util.DataDummy
import com.comfest.seatudy.util.DataDummy.generateProgressCourseResponse
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
class DashboardViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var dashboardDetail: DashboardViewModel

    @Mock
    private lateinit var seatudyRepository: SeatudyRepository

    @Mock
    private lateinit var pref: SettingsPreferences

    private val dummyCourseListDetailResponse = DataDummy.generateCourseListDetailResponse()
    private val dummyEnrollCourseResponse = DataDummy.generateEnrollCourseResponse()
    private val dummyProgressCourseResponse = generateProgressCourseResponse()

    @Before
    fun setUp() {
        dashboardDetail = DashboardViewModel(pref, seatudyRepository)
    }

    @Test
    fun `when Get Course With ID Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseCoursesListDetail>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyCourseListDetailResponse))
        Mockito.`when`(seatudyRepository.getCoursesWithID("")).thenReturn(expectedResponse)

        val actualResponse = dashboardDetail.getCoursesWithID("").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Get Enrolled Course Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseEnrollCourse>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyEnrollCourseResponse))
        Mockito.`when`(seatudyRepository.getEnrolledCourse("")).thenReturn(expectedResponse)

        val actualResponse = dashboardDetail.getEnrolledCourse("").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Get Progress Course Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseProgress>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyProgressCourseResponse))
        Mockito.`when`(seatudyRepository.getProgress("", "")).thenReturn(expectedResponse)

        val actualResponse = dashboardDetail.getProgress("", "").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }


}