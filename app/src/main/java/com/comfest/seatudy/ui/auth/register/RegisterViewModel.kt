package com.comfest.seatudy.ui.auth.register

import androidx.lifecycle.ViewModel
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.domain.model.DataRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: SeatudyRepository
) : ViewModel() {

    fun register(data: DataRegister) = repo.register(data)

}