package com.comfest.seatudy.ui.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.respon.ResponseProfile
import com.comfest.seatudy.data.source.respon.ResponseTopUp
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.util.DataDummy.generateProfileResponse
import com.comfest.seatudy.util.DataDummy.generateTopUp
import com.comfest.seatudy.util.DataDummy.generateTopUpResponse
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
class ProfileViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var profileViewModel: ProfileViewModel

    @Mock
    private lateinit var seatudyRepository: SeatudyRepository

    @Mock
    private lateinit var pref: SettingsPreferences

    private val dummyProfileResponse = generateProfileResponse()
    private val dummyTopUpResponse = generateTopUpResponse()
    private val dummyTopUp = generateTopUp()


    @Before
    fun setUp() {
        profileViewModel = ProfileViewModel(pref, seatudyRepository)
    }

    @Test
    fun `when Get Profile Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseProfile>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyProfileResponse))
        Mockito.`when`(seatudyRepository.getProfile("")).thenReturn(expectedResponse)

        val actualResponse = profileViewModel.getProfile("").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Top Up Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<ResponseTopUp>>()
        expectedResponse.value = Resource.Success(dummyTopUpResponse)
        Mockito.`when`(seatudyRepository.topUp(dummyTopUp, "")).thenReturn(expectedResponse)

        val actualResponse = profileViewModel.topUp(dummyTopUp, "").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }
}