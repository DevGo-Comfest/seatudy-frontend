package com.comfest.seatudy.ui.cart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.respon.ResponseEnrollments
import com.comfest.seatudy.data.source.respon.ResponseProfile
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.ui.cart.payment.PaymentViewModel
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
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class PaymentViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var paymentViewModel: PaymentViewModel

    @Mock
    private lateinit var seatudyRepository: SeatudyRepository

    @Mock
    private lateinit var pref: SettingsPreferences

    private val dummyProfileResponse = DataDummy.generateProfileResponse()

    private val dummyBuyResponse = DataDummy.generateBuyResponse()
    private val dummyBuy = DataDummy.generateBuy()

    @Before
    fun setUp() {
        paymentViewModel = PaymentViewModel(pref, seatudyRepository)
    }

    @Test
    fun `when buyCourse Should Not Null and Return Success`() {
        val expectedResponse = MutableLiveData<Resource<Response<ResponseEnrollments>>>()
        expectedResponse.value = Resource.Success(Response.success(dummyBuyResponse))
        `when`(seatudyRepository.buyCourse(dummyBuy, "")).thenReturn(expectedResponse)

        val actualResponse = paymentViewModel.buyCourse(dummyBuy, "").getOrAwaitValue()

        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Resource.Success)
        Assert.assertEquals(expectedResponse.value, actualResponse)
    }

    @Test
    fun `when Get Profile Should Not Null and Return Success`() {
        val expectedProfile = MutableLiveData<Resource<Response<ResponseProfile>>>()
        expectedProfile.value = Resource.Success(Response.success(dummyProfileResponse))
        `when`(seatudyRepository.getProfile("")).thenReturn(expectedProfile)

        val actualProfile = paymentViewModel.getProfile("").getOrAwaitValue()  // Assuming getOrAwaitValue() is an extension function to get the LiveData value

        Assert.assertNotNull(actualProfile)
        Assert.assertTrue(actualProfile is Resource.Success)
        Assert.assertEquals(expectedProfile.value, actualProfile)
    }

}