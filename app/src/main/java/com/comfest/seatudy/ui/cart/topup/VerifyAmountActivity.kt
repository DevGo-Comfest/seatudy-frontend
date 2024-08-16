package com.comfest.seatudy.ui.cart.topup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityVerifyAmountBinding
import com.comfest.seatudy.domain.model.DataTopUp
import com.comfest.seatudy.ui.NavigationActivity
import com.comfest.seatudy.ui.profile.ProfileViewModel
import com.comfest.seatudy.utils.ToastResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyAmountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerifyAmountBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyAmountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        binding.btnTopUp.setOnClickListener {
            topUp()
        }
    }

    private fun topUp() {
        val amount = binding.tvBalance.text.toString()
        val payment = intent.getStringExtra("PAYMENT").toString()

        profileViewModel.getToken().observe(this) {
            profileViewModel.topUp(DataTopUp(amount.toInt(), payment), "Bearer $it").observe(this) { respon ->
                when (respon) {
                    is Resource.Loading -> {
                        ToastResource.toastResource("Loading", this@VerifyAmountActivity)
                    }

                    is Resource.Success -> {
                        startActivity(Intent(this, NavigationActivity::class.java))
                        finish()
                    }

                    is Resource.Error -> {
                        ToastResource.toastResource("Error Occurred", this@VerifyAmountActivity)
                    }
                }

            }
        }
    }
}