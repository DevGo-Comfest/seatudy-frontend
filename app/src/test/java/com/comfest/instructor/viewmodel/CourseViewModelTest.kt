@file:Suppress("DEPRECATION")

package com.comfest.instructor.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import com.comfest.instructor.data.InstructorRepository
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.data.source.remote.response.CreateCourseResponse
import com.comfest.instructor.domain.model.RequestCreateCourse
import com.comfest.instructor.ui.course.CourseViewModel
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.utils.SettingsPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import retrofit2.HttpException
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class CourseViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var viewModel: CourseViewModel

    @Mock
    private lateinit var instructorRepository: InstructorRepository

    @Mock
    private lateinit var settingsPreferences: SettingsPreferences


    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private val requestCreateCourse = RequestCreateCourse("Course Title", "Course Description", 10000, "Android","https:jsahdad", "beginner")
    private val course = Course(
        1,
        "asda",
        "Course Title",
        "Course Description",
        10000,
        "Android",
        "https:jsahdad",
        "beginner",
        "2024-10-10",
        "2024-10-10",
        4,
        "active",
        false,
        "",
        "",
        "",
        "",
        ""
    )


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = CourseViewModel(instructorRepository, settingsPreferences)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original state
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `test uploadImage handles error correctly`() = testScope.runBlockingTest {
        // Arrange
        val token = "dummy_token"
        val imagePart = MultipartBody.Part.createFormData("file", "image.jpg", RequestBody.create(
            "image/jpeg".toMediaTypeOrNull(), byteArrayOf()))
        val expectedError = HttpException(Response.error<Any>(500, "Internal Server Error".toResponseBody()))

        `when`(instructorRepository.uploadImage("Bearer $token", imagePart)).thenThrow(expectedError)


        try {
            viewModel.uploadImage(token, imagePart)
            fail("Exception should be thrown")
        } catch (e: HttpException) {
            assertEquals(expectedError.code(), e.code())
        }
    }

    @Test
    fun `test createCourse success`() = testScope.runBlockingTest {
        val token = "dummy_token"
        val expectedResponse = CreateCourseResponse(
            course, "success created"
        )

        val resource = Resource.Success(expectedResponse)

        `when`(instructorRepository.createCourse("Bearer $token", requestCreateCourse)).thenReturn(resource.toLiveData())

        val observer = mock<Observer<Resource<CreateCourseResponse>>>()
        viewModel.crateCourse(token, requestCreateCourse).observeForever(observer)

        verify(instructorRepository).createCourse("Bearer $token", requestCreateCourse)

        verify(observer).onChanged(resource)
    }

    @Test
    fun `test createCourse fail`() = testScope.runBlockingTest {
        val token = "dummy_token"

        val resource = Resource.Error<CreateCourseResponse>("error")

        `when`(instructorRepository.createCourse("Bearer $token", requestCreateCourse)).thenReturn(resource.toLiveData())

        val observer = mock<Observer<Resource<CreateCourseResponse>>>()
        viewModel.crateCourse(token, requestCreateCourse).observeForever(observer)

        verify(instructorRepository).createCourse("Bearer $token", requestCreateCourse)

        verify(observer).onChanged(resource)
    }

    @Test
    fun `getToken should return null when no token available`() {
        `when`(settingsPreferences.getTokenUser()).thenReturn(MutableLiveData<String>().asFlow())


        val actualToken = viewModel.getToken().value

        assertNull(actualToken)
    }


    @Test
    fun `getToken should return the correct token`() {
        val expectedToken = "asdadad123123wqe12331w31231231231232112"
        val liveDataToken = MutableLiveData<String>()
        liveDataToken.value = expectedToken

        `when`(settingsPreferences.getTokenUser()).thenReturn(liveDataToken.asFlow())

        val observer = mock(Observer::class.java) as Observer<*>
        viewModel.getToken().observeForever(observer as Observer<in String>)

        verify(observer).onChanged(expectedToken)
    }



    private fun <T> Resource<T>.toLiveData(): LiveData<Resource<T>> {
        return MutableLiveData(this)
    }

}