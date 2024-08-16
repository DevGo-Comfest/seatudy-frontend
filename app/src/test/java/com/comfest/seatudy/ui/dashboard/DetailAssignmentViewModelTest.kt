package com.comfest.seatudy.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.respon.ResponseSubmissions
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.ui.dashboard.detailsubmission.DetailAssignmentViewModel
import com.comfest.seatudy.util.DataDummy
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
class DetailAssignmentViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var detailAssignmentViewModel: DetailAssignmentViewModel

    @Mock
    private lateinit var seatudyRepository: SeatudyRepository

    private var dummySendAssignmentResponse = DataDummy.generateSendAssignmentResponse()
    private val dummySendAssignment = DataDummy.generateSendAssignment()

    @Mock
    private lateinit var pref: SettingsPreferences


    @Before
    fun setUp() {
        detailAssignmentViewModel = DetailAssignmentViewModel(pref, seatudyRepository)
    }

    @Test
    fun `when Send Status Assignment With ID Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseSubmissions>>>()
        expectedResponse.value = Resource.Success(Response.success(dummySendAssignmentResponse))
        Mockito.`when`(seatudyRepository.sendAssignment("", dummySendAssignment, "")).thenReturn(expectedResponse)

        val actualResponse = detailAssignmentViewModel.sendAssignment("", dummySendAssignment, "").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }
}