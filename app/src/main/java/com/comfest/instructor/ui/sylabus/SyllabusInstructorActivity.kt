package com.comfest.instructor.ui.sylabus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.ActivitySyllabusInstructorBinding

class SyllabusInstructorActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySyllabusInstructorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySyllabusInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}