package com.comfest.seatudy.ui.cart.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityPaymentBinding
import com.comfest.seatudy.domain.model.DataBuy
import com.comfest.seatudy.ui.NavigationActivity
import com.comfest.seatudy.ui.cart.topup.TopUpActivity
import com.comfest.seatudy.utils.ToastResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding
    private lateinit var paymentViewModel: PaymentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        paymentViewModel = ViewModelProvider(this)[PaymentViewModel::class.java]

        binding.btnTopUp.setOnClickListener {
            startActivity(Intent(this, TopUpActivity::class.java))
        }

        buyCourse()
        balance()
    }

    private fun buyCourse() {
        val courseID = intent.getStringExtra("COURSEID")
        val titleCourse = intent.getStringExtra("TITLE")
        val img = intent.getStringExtra("IMG")
        val price = intent.getStringExtra("PRICE")

        binding.apply {
            tvNameCourse.text = titleCourse
            tvPrice.text = price
            tvPriceCard.text = price


            Glide.with(this@PaymentActivity)
                .load(img)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.imgCourse)
        }

        binding.btnProcessPayment.setOnClickListener {
            paymentViewModel.getToken().observe(this) { token ->
                Log.d("CEK", "${courseID!!.toInt()}")
                paymentViewModel.buyCourse(DataBuy(courseID.toInt()), "Bearer $token")
                    .observe(this) {
                        when (it) {
                            is Resource.Loading -> {
                                ToastResource.toastResource("Loading", this@PaymentActivity)
                            }

                            is Resource.Success -> {
                                ToastResource.toastResource("Success Payment", this@PaymentActivity)
                                startActivity(Intent(this, NavigationActivity::class.java))
                            }

                            is Resource.Error -> {
                                ToastResource.toastResource("Error Occurred", this@PaymentActivity)
                            }
                        }
                    }
            }
        }
    }

    private fun balance() {
        paymentViewModel.getToken().observe(this) { token ->
            paymentViewModel.getProfile("Bearer $token").observe(this) { value ->
                when (value) {
                    is Resource.Loading -> {
                        ToastResource.toastResource("Loading", this@PaymentActivity)
                    }

                    is Resource.Success -> {
                        val data = value.data?.body()
                        binding.tvBalance.text = data?.balance.toString()
                    }

                    is Resource.Error -> {
                        ToastResource.toastResource("Error Occurred", this@PaymentActivity)
                    }
                }
            }
        }
    }
}