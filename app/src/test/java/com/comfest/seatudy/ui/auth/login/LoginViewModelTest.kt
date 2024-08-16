package com.comfest.seatudy.ui.auth.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.respon.ResponseLogin
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.util.DataDummy
import com.comfest.seatudy.util.getOrAwaitValue
import com.comfest.seatudy.utils.SettingsPreferences
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest{

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var loginViewModel: LoginViewModel

    @Mock
    private lateinit var seatudyRepository: SeatudyRepository

    @Mock
    private lateinit var pref: SettingsPreferences

    private val dummyLoginResponse = DataDummy.generateLoginResponse()
    private val dummyLogin = DataDummy.generateLogin()

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(pref, seatudyRepository)
    }

    @Test
    fun `when Login Should Not Null and Return Success`() {
        val expectedResult = MutableLiveData<Resource<ResponseLogin>>()
        expectedResult.value = Resource.Success(dummyLoginResponse)
        Mockito.`when`(seatudyRepository.login(dummyLogin)).thenReturn(expectedResult)

        val actualResult = loginViewModel.login(dummyLogin).getOrAwaitValue()

        Mockito.verify(seatudyRepository).login(dummyLogin)
        Assert.assertNotNull(actualResult)
        Assert.assertTrue(actualResult is Resource.Success)
        Assert.assertEquals(expectedResult.value, actualResult)
    }

    @Test
    fun `when Login Should Not Null and Return Error`() {
        val expectedResult = MutableLiveData<Resource<ResponseLogin>>()
        expectedResult.value = Resource.Error("Unknown Error")

        Mockito.`when`(seatudyRepository.login(dummyLogin)).thenReturn(expectedResult)

        val actualResult = loginViewModel.login(dummyLogin).getOrAwaitValue()

        Mockito.verify(seatudyRepository).login(dummyLogin)
        Assert.assertNotNull(actualResult)
        Assert.assertTrue(actualResult is Resource.Error)
        Assert.assertEquals(expectedResult.value, actualResult)
    }
}