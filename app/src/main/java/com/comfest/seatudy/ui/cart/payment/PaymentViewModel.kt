package com.comfest.seatudy.ui.cart.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.comfest.seatudy.domain.model.DataBuy
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val repo: SeatudyRepository
) : ViewModel() {

    fun buyCourse(dataBuy: DataBuy, token: String) = repo.buyCourse(dataBuy, token)

    fun getProfile(token: String) = repo.getProfile(token)

    fun getToken(): LiveData<String> {
        return pref.getTokenUser().asLiveData()
    }
}