package com.comfest.seatudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.comfest.seatudy.databinding.ActivityMainBinding
import com.comfest.seatudy.ui.NavigationActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val time: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Glide.with(this)
            .load(R.drawable.logo)
            .into(binding.imgLogo)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, NavigationActivity::class.java))
            finish()
        }, time)
    }
}