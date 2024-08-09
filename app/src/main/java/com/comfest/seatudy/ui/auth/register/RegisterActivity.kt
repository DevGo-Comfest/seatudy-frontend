package com.comfest.seatudy.ui.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.comfest.seatudy.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}