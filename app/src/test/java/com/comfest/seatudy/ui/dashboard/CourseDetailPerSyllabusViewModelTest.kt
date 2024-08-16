package com.comfest.seatudy.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.respon.ResponseOpenAssignment
import com.comfest.seatudy.data.source.respon.ResponseSyllabus
import com.comfest.seatudy.data.source.respon.ResponseUserAssignment
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.ui.dashboard.detailpersyllabus.CourseDetailPerSyllabusViewModel
import com.comfest.seatudy.util.DataDummy.generateGetSyllabusResponse
import com.comfest.seatudy.util.DataDummy.generateOpenAssignment
import com.comfest.seatudy.util.DataDummy.generateOpenAssignmentResponse
import com.comfest.seatudy.util.DataDummy.generatePerSyllabusResponse
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
class CourseDetailPerSyllabusViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var courseDetailPerSyllabus: CourseDetailPerSyllabusViewModel

    @Mock
    private lateinit var seatudyRepository: SeatudyRepository

    @Mock
    private lateinit var pref: SettingsPreferences

    private val dummyPerSyllabusResponse = generatePerSyllabusResponse()

    private val dummyOpenAssignmentResponse = generateOpenAssignmentResponse()
    private val dummyGenerateOpenAssignment = generateOpenAssignment()

    private val dummyGetSyllabusResponse = generateGetSyllabusResponse()

    @Before
    fun setUp() {
        courseDetailPerSyllabus = CourseDetailPerSyllabusViewModel(pref, seatudyRepository)
    }

    @Test
    fun `when Get User Assignment Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseUserAssignment>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyPerSyllabusResponse))
        Mockito.`when`(seatudyRepository.getUserAssignment("", "")).thenReturn(expectedResponse)

        val actualResponse = courseDetailPerSyllabus.getUserAssignment("", "").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Send Open Assignment Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseOpenAssignment>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyOpenAssignmentResponse))
        Mockito.`when`(seatudyRepository.sendOpenAssignment(dummyGenerateOpenAssignment, "")).thenReturn(expectedResponse)

        val actualResponse = courseDetailPerSyllabus.sendOpenAssignment(dummyGenerateOpenAssignment, "").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Get Syllabus Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseSyllabus>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyGetSyllabusResponse))
        Mockito.`when`(seatudyRepository.getSyllabus("")).thenReturn(expectedResponse)

        val actualResponse = courseDetailPerSyllabus.getSyllabus("").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

}