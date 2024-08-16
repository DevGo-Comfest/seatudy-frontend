package com.comfest.seatudy.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.respon.ResponseCoursesList
import com.comfest.seatudy.data.source.respon.ResponseCoursesListDetailCategory
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.util.DataDummy.generateCourseCategoryResponse
import com.comfest.seatudy.util.DataDummy.generateCourseResponse
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
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var seatudyRepository: SeatudyRepository

    @Mock
    private lateinit var pref: SettingsPreferences

    private var dummyCourseResponse = generateCourseResponse()
    private var dummyCourseCategoryResponse = generateCourseCategoryResponse()


    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(pref, seatudyRepository)
    }

    @Test
    fun `when Get Course All Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseCoursesList>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyCourseResponse))
        Mockito.`when`(seatudyRepository.getCourses()).thenReturn(expectedResponse)

        val actualResponse = homeViewModel.getCourse().getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Get Course Category Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseCoursesListDetailCategory>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyCourseCategoryResponse))
        Mockito.`when`(seatudyRepository.getCourseCategory("")).thenReturn(expectedResponse)

        val actualResponse = homeViewModel.getCourseCategory("").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Get Search Course Name All Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseCoursesListDetailCategory>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyCourseCategoryResponse))
        Mockito.`when`(seatudyRepository.getSearchCourseName("")).thenReturn(expectedResponse)

        val actualResponse = homeViewModel.getSearchCourseName("").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Get Search Course Category Level Rating Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseCoursesListDetailCategory>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyCourseCategoryResponse))
        Mockito.`when`(seatudyRepository.getSearchCategoryLevelRating("", "", "")).thenReturn(expectedResponse)

        val actualResponse = homeViewModel.getSearchCategoryLevelRating("", "", "").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

}