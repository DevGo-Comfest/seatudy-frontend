package com.comfest.seatudy.ui.auth

import com.comfest.seatudy.data.DataDummy
import com.comfest.seatudy.domain.repository.SeatudyRepository
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest{

    @Mock
    private lateinit var newsRepository : SeatudyRepository
    private lateinit var newsViewModel: LoginViewModelTest

//    private val dummyNews = DataDummy.generateDummyNewsEntity()
//    @Before
//    fun setUp() {
//        newsViewModel = LoginViewModelTest(newsRepository)
//    }

//    @Test
//    fun `when Get HeadlineNews Should Not Null and Return Success`() {
//        val expectedNews = MutableLiveData<Result<List<NewsEntity>>>() //Result diambil dari package data yang sudah disiapkan pada starter project
//        expectedNews.value = Result.Success(dummyNews)
//        `when`(newsRepository.getHeadlineNews()).thenReturn(expectedNews)
//        val actualNews = newsViewModel.getHeadlineNews()
//        Assert.assertNotNull(actualNews)
//    }
}