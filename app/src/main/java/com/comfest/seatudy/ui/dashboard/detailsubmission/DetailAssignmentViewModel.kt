package com.comfest.seatudy.ui.dashboard.detailsubmission

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.comfest.seatudy.domain.model.DataUrlAssignment
import com.comfest.seatudy.domain.repository.SeatudyRepository
import com.comfest.seatudy.utils.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailAssignmentViewModel @Inject constructor(
    private val pref: SettingsPreferences,
    private val repo: SeatudyRepository
) : ViewModel() {

    fun sendAssignment(id: String, dataUrl: DataUrlAssignment, token: String) = repo.sendAssignment(id, dataUrl, token)

    fun getToken(): LiveData<String> {
        return pref.getTokenUser().asLiveData()
    }
}