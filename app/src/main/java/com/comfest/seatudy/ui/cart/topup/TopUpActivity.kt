package com.comfest.seatudy.ui.cart.topup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.comfest.seatudy.databinding.ActivityTopUpBinding
import com.comfest.seatudy.domain.model.DataPayment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTopUpBinding
    private lateinit var adapterTopUp: AdapterTopUp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterTopUp()
    }

    private fun adapterTopUp() {
        val listTopUp = listOf(
            DataPayment("Alfamart", "https://raw.githubusercontent.com/DewaTriWijaya/ImageAsset/main/image/payment/payment_alfa.png"),
            DataPayment("Indomaret", "https://raw.githubusercontent.com/DewaTriWijaya/ImageAsset/main/image/payment/payment_indo.png"),
            DataPayment("Gopay", "https://raw.githubusercontent.com/DewaTriWijaya/ImageAsset/main/image/payment/payment_gopay.png"),
            DataPayment("Dana", "https://raw.githubusercontent.com/DewaTriWijaya/ImageAsset/main/image/payment/payment_dana.png"),
            DataPayment("BRI", "https://raw.githubusercontent.com/DewaTriWijaya/ImageAsset/main/image/payment/payment_bri.png"),
            DataPayment("BNI", "https://raw.githubusercontent.com/DewaTriWijaya/ImageAsset/main/image/payment/payment_bni.png")

        )
        adapterTopUp = AdapterTopUp(listTopUp)
        binding.rvTopUp.layoutManager = GridLayoutManager(this, 2)
        binding.rvTopUp.adapter = adapterTopUp
    }
}