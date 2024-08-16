@file:Suppress("DEPRECATION")

package com.comfest.instructor.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import com.comfest.instructor.data.InstructorRepository
import com.comfest.instructor.domain.model.RequestCreateDiscussion
import com.comfest.instructor.ui.discussion.DiscussionViewModel
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
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class DiscussionViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var instructorRepository: InstructorRepository

    @Mock
    private lateinit var settingsPreferences: SettingsPreferences

    private lateinit var discussionViewModel: DiscussionViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        discussionViewModel = DiscussionViewModel(instructorRepository, settingsPreferences)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getDiscussion should call repository with correct parameters`() = runBlockingTest {
        val courseId = 1
        val token = "token"
        discussionViewModel.getDiscussion(courseId, token)

        verify(instructorRepository).getDiscussion(courseId, "Bearer $token")
    }

    @Test
    fun `createMessageDiscussion should call repository with correct parameters`() = runBlockingTest {
        val token = "token"
        val requestCreateDiscussion = RequestCreateDiscussion(1, "test")

        discussionViewModel.createMessageDiscussion(token, requestCreateDiscussion)

        verify(instructorRepository).createMessageDiscussion("Bearer $token", requestCreateDiscussion)
    }

    @Test
    fun `getToken should return LiveData with correct value`() {
        val expectedToken = "token"
        val liveData = MutableLiveData<String>()
        liveData.value = expectedToken

        whenever(settingsPreferences.getTokenUser()).thenReturn(liveData.asFlow())

        val observer: Observer<String> = mock()
        discussionViewModel.getToken().observeForever(observer)

        verify(observer).onChanged(expectedToken)
    }
}

