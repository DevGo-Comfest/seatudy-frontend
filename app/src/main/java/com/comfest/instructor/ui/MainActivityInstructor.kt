package com.comfest.instructor.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.ActivityMainInstructorBinding

class MainActivityInstructor : AppCompatActivity() {

    private lateinit var binding: ActivityMainInstructorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}