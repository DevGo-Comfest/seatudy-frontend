package com.comfest.seatudy.ui.dashboard.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.ActivityCourseDetailBinding
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailSyllabusActivity
import com.google.android.material.tabs.TabLayout

class CourseDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartCourse.setOnClickListener {
            startActivity(Intent(this@CourseDetailActivity, CourseDetailSyllabusActivity::class.java))
        }

        tabLayout()
    }

    private fun tabLayout(){
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }
}