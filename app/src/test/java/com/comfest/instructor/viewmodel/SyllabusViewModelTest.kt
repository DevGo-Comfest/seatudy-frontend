@file:Suppress("DEPRECATION")

package com.comfest.instructor.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.comfest.instructor.data.InstructorRepository
import com.comfest.instructor.domain.model.RequestCreateAssignment
import com.comfest.instructor.domain.model.RequestCreateSyllabus
import com.comfest.instructor.domain.model.RequestCreateSyllabusMaterial
import com.comfest.instructor.domain.model.RequestUpdateSyllabus
import com.comfest.instructor.domain.model.RequestUpdateSyllabusMaterial
import com.comfest.instructor.ui.sylabus.SyllabusViewModel
import com.comfest.seatudy.utils.SettingsPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class SyllabusViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var instructorRepository: InstructorRepository

    @Mock
    private lateinit var settingsPreferences: SettingsPreferences

    private lateinit var syllabusViewModel: SyllabusViewModel


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        syllabusViewModel = SyllabusViewModel(instructorRepository, settingsPreferences)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `createSyllabus should call repository with correct parameters`() = runBlockingTest {
        val token = "token"
        val requestCreateSyllabus = RequestCreateSyllabus("Test", "desc", 4)

        syllabusViewModel.createSyllabus(token, requestCreateSyllabus)

        verify(instructorRepository).createSyllabus("Bearer $token", requestCreateSyllabus)
    }

    @Test
    fun `createAssignment should call repository with correct parameters`() = runBlockingTest {
        val id = 1
        val token = "token"
        val requestCreateAssignment = RequestCreateAssignment("test assignment", "desc", 4)

        syllabusViewModel.createAssignment(id, token, requestCreateAssignment)

        verify(instructorRepository).createAssignment(id, "Bearer $token", requestCreateAssignment)
    }

    @Test
    fun `getDetailCourse should call repository with correct parameters`() = runBlockingTest {
        val courseId = 1
        val token = "token"

        syllabusViewModel.getDetailCourse(courseId, token)

        verify(instructorRepository).getDetailCourse(courseId, "Bearer $token")
    }

    @Test
    fun `getAssignmentBySyllabusId should call repository with correct parameters`() = runBlockingTest {
        val syllabusId = 1
        val token = "token"

        syllabusViewModel.getAssignmentBySyllabusId(syllabusId, token)

        verify(instructorRepository).getAssignmentBySyllabusId(syllabusId, "Bearer $token")
    }

    @Test
    fun `updateSyllabus should call repository with correct parameters`() = runBlockingTest {
        val syllabusId = 1
        val token = "token"
        val requestUpdateSyllabus = RequestUpdateSyllabus("update",  "desc update")

        syllabusViewModel.updateSyllabus(syllabusId, token, requestUpdateSyllabus)

        verify(instructorRepository).updateSyllabus(syllabusId, "Bearer $token", requestUpdateSyllabus)
    }

    @Test
    fun `deleteSyllabus should call repository with correct parameters`() = runBlockingTest {
        val syllabusId = 1
        val token = "token"

        syllabusViewModel.deleteSyllabus(syllabusId, token)

        verify(instructorRepository).deleteSyllabus(syllabusId, "Bearer $token")
    }

    @Test
    fun `updateAssignment should call repository with correct parameters`() = runBlockingTest {
        val assignmentId = 1
        val token = "token"
        val requestCreateAssignment = RequestCreateAssignment("update", "desc update", 4)

        syllabusViewModel.updateAssignment(assignmentId, token, requestCreateAssignment)

        verify(instructorRepository).updateAssignment(assignmentId, "Bearer $token", requestCreateAssignment)
    }

    @Test
    fun `deleteAssignment should call repository with correct parameters`() = runBlockingTest {
        val assignmentId = 1
        val token = "token"

        syllabusViewModel.deleteAssignment(assignmentId, token)

        verify(instructorRepository).deleteAssignment(assignmentId, "Bearer $token")
    }

    @Test
    fun `createSyllabusMaterial should call repository with correct parameters`() = runBlockingTest {
        val token = "token"
        val dummyRequestCreateSyllabusMaterial = RequestCreateSyllabusMaterial(
            title = "Introduction to Kotlin",
            description = "This material covers the basics of Kotlin programming language.",
            urlMaterial = "https://example.com/kotlin-introduction.pdf",
            timeNeeded = "2 hours",
            syllabusID = 10
        )

        syllabusViewModel.createSyllabusMaterial(token, dummyRequestCreateSyllabusMaterial)

        verify(instructorRepository).createSyllabusMaterial(
            "Bearer $token",
            dummyRequestCreateSyllabusMaterial
        )
    }

    @Test
    fun `getSyllabusMaterialById should call repository with correct parameters`() = runBlockingTest {
        val syllabusId = 1
        val token = "token"

        syllabusViewModel.getSyllabusMaterialById(syllabusId, token)

        verify(instructorRepository).getSyllabusMaterialById(syllabusId, "Bearer $token")
    }

    @Test
    fun `updateSyllabusMaterial should call repository with correct parameters`() = runBlockingTest {
        val syllabusMaterialId = 1
        val token = "token"
        val dummyRequestUpdateSyllabusMaterial = RequestUpdateSyllabusMaterial(
            title = "Advanced Kotlin Techniques",
            description = "This material delves into advanced concepts of Kotlin programming.",
            urlMaterial = "https://example.com/advanced-kotlin.pdf",
            timeNeeded = "3 hours"
        )

        syllabusViewModel.updateSyllabusMaterial(syllabusMaterialId, token, dummyRequestUpdateSyllabusMaterial)

        verify(instructorRepository).updateSyllabusMaterial(syllabusMaterialId, "Bearer $token", dummyRequestUpdateSyllabusMaterial)
    }

    @Test
    fun `deleteSyllabusMaterial should call repository with correct parameters`() = runBlockingTest {
        val syllabusMaterialId = 1
        val token = "token"

        syllabusViewModel.deleteSyllabusMaterial(syllabusMaterialId, token)

        verify(instructorRepository).deleteSyllabusMaterial(syllabusMaterialId, "Bearer $token")
    }
}