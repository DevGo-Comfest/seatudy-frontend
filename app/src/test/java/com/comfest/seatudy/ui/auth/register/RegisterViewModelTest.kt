package com.comfest.seatudy.ui.auth.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.respon.ResponseRegister
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.util.DataDummy
import com.comfest.seatudy.util.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegisterViewModelTest {
    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var signUpViewModel: RegisterViewModel

    @Mock
    private lateinit var seatudyRepository: SeatudyRepository
    private val dummyRegisterResponse = DataDummy.generateRegisterResponse()
    private val dummyRegister = DataDummy.generateRegister()

    @Before
    fun setUp() {
        signUpViewModel = RegisterViewModel(seatudyRepository)
    }

    @Test
    fun `when Sign Up Should Not Null and Return Success`() {
        val expectedResult = MutableLiveData<Resource<ResponseRegister>>()
        expectedResult.value = Resource.Success(dummyRegisterResponse)

        Mockito.`when`(seatudyRepository.register(dummyRegister)).thenReturn(expectedResult)

        val actualResult = signUpViewModel.register(dummyRegister).getOrAwaitValue()

        Mockito.verify(seatudyRepository).register(dummyRegister)
        Assert.assertNotNull(actualResult)
        Assert.assertTrue(actualResult is Resource.Success)
        Assert.assertEquals(expectedResult.value, actualResult)
    }

    @Test
    fun `when Sign Up Should Not Null and Return Error`() {
        val expectedResult = MutableLiveData<Resource<ResponseRegister>>()
        expectedResult.value = Resource.Error("Unknown Error")

        Mockito.`when`(seatudyRepository.register(dummyRegister)).thenReturn(expectedResult)

        val actualResult = signUpViewModel.register(dummyRegister).getOrAwaitValue()

        Mockito.verify(seatudyRepository).register(dummyRegister)
        Assert.assertNotNull(actualResult)
        Assert.assertTrue(actualResult is Resource.Error)
        Assert.assertEquals(expectedResult.value, actualResult)
    }
}