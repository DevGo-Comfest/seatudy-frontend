package com.comfest.seatudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.comfest.instructor.ui.MainActivityInstructor
import com.comfest.seatudy.databinding.ActivityMainBinding
import com.comfest.seatudy.ui.NavigationActivity
import com.comfest.seatudy.ui.auth.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val time: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        Glide.with(this)
            .load(R.drawable.logo)
            .into(binding.imgLogo)

        Handler(Looper.getMainLooper()).postDelayed({
            mainViewModel.getLoginUser().observe(this) { login: Boolean ->
                mainViewModel.getRoleUser().observe(this) { role ->
                    if (login && role == "user") {
                        startActivity(Intent(this, NavigationActivity::class.java))
                        finish()
                    } else if (login && role == "author") {
                        startActivity(Intent(this, MainActivityInstructor::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }

            }
        }, time)
    }
}