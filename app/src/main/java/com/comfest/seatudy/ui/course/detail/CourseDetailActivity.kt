package com.comfest.seatudy.ui.course.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.ActivityCourseDetailBinding

class CourseDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}