@file:Suppress("DEPRECATION")

package com.comfest.instructor.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import com.comfest.instructor.data.InstructorRepository
import com.comfest.instructor.data.source.remote.response.DataSubmissionUserResponse
import com.comfest.instructor.data.source.remote.response.Instructor
import com.comfest.instructor.data.source.remote.response.InstructorResponse
import com.comfest.instructor.data.source.remote.response.Submission
import com.comfest.instructor.domain.model.RequestAddGradeSubmission
import com.comfest.instructor.ui.assignment.AssignViewModel
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.utils.SettingsPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response


@ExperimentalCoroutinesApi
class AssignViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val settingsPreferences: SettingsPreferences = mock(SettingsPreferences::class.java)
    private val instructorRepository: InstructorRepository = mock(InstructorRepository::class.java)
    private val viewModel = AssignViewModel(instructorRepository, settingsPreferences)


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `getInstructor should return instructor data`() {
        val expectedInstructorData = InstructorResponse(
            listOf(Instructor("3", "ibra", "ibra@gmail.com", "1233", 2000, "author", "2024-10-10","2024-10-10", "", "", "", "", "", "", "", ""))
        )
        val response = Response.success(expectedInstructorData)

        val resource = Resource.Success(response)

            `when`(instructorRepository.getInstructor()).thenReturn(resource.toLiveData())

        // Panggil metode dari ViewModel
        val liveData = viewModel.getInstructor()

        liveData.observeForever { res ->
            when (res) {
                is Resource.Success -> {
                    val actualData = resource.data
                    assertEquals(expectedInstructorData, actualData?.body())
                }
                else -> fail("Expected Resource.Success but got ${resource.javaClass.simpleName}")
            }
        }
    }

    @Test
    fun `getInstructor should return an error`() {
        // Definisikan pesan error yang diharapkan
        val errorMessage = "Network error"
        // Mock response dengan Resource.Error
        val errorResource = Resource.Error<Response<InstructorResponse>>(errorMessage)


        // Ketika getInstructor dipanggil, kembalikan Resource.Error
            `when`(instructorRepository.getInstructor()).thenReturn(errorResource.toLiveData())

        // Panggil metode dari ViewModel
        val liveData = viewModel.getInstructor()

        // Observasi nilai LiveData
        liveData.observeForever { resource ->
            when (resource) {
                is Resource.Error -> {
                    // Verifikasi bahwa resource adalah Resource.Error dan pesan error sesuai yang diharapkan
                    assertEquals(errorMessage, resource.message)
                }
                else -> fail("Expected Resource.Error but got ${resource.javaClass.simpleName}")
            }
        }
    }



    @Test
    fun `getSubmissionUser should return null when syllabus id not found`() {
        val dummySubmissions = listOf(
            Submission(
                submissionID = 1,
                status = "Completed",
                grade = 85,
                contentURL = "https://example.com/submission1.pdf",
                isLate = false,
                assignmentID = 101,
                userID = "user123",
                createdAt = "2024-08-01T10:00:00Z",
                updatedAt = "2024-08-02T15:00:00Z"
            ),
            Submission(
                submissionID = 2,
                status = "Pending",
                grade = 0,
                contentURL = "https://example.com/submission2.pdf",
                isLate = false,
                assignmentID = 102,
                userID = "user124",
                createdAt = "2024-08-03T12:00:00Z",
                updatedAt = "2024-08-04T16:00:00Z"
            ),
            Submission(
                submissionID = 3,
                status = "Late",
                grade = 70,
                contentURL = "https://example.com/submission3.pdf",
                isLate = true,
                assignmentID = 103,
                userID = "user125",
                createdAt = "2024-08-04T09:00:00Z",
                updatedAt = "2024-08-05T11:00:00Z"
            )
        )

        val dummyDataSubmissionUserResponse = DataSubmissionUserResponse(
            assignmentID = 101,
            syllabusID = 201,
            title = "Assignment 1",
            description = "This is the first assignment description.",
            maximumTime = 120,
            submissions = dummySubmissions,
            userAssignments = null // Replace with appropriate dummy data if needed
        )
        val response = Response.success(dummyDataSubmissionUserResponse)

        val resource = Resource.Success(response)
        val token = "token_user"


        `when`(instructorRepository.getSubmissionUser(-1, token)).thenReturn(resource.toLiveData())

        // Panggil metode dari ViewModel
        val result = viewModel.getSubmissionUser(-1, token)

        assertNull(result)
    }




    @Test
    fun `addGrade should call repository with correct parameters`() {
        val submissionId = 1
        val token = "valid_token"
        val requestAddGradeSubmission = RequestAddGradeSubmission(
            90
        )

        viewModel.addGrade(submissionId, token, requestAddGradeSubmission)
        verify(instructorRepository).addGrade(submissionId, "Bearer $token", requestAddGradeSubmission)
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


    private fun <T> Resource<Response<T>>.toLiveData(): LiveData<Resource<Response<T>>> {
        return MutableLiveData(this)
    }

//    private fun <T> Resource<T>.toLiveData(): LiveData<Resource<T>> {
//        return MutableLiveData(this)
//    }


}