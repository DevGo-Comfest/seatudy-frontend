package com.comfest.seatudy.ui.dashboard.detailcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.comfest.seatudy.databinding.ActivityCourseDetailSyllabusBinding
import com.google.android.material.tabs.TabLayout

class CourseDetailSyllabusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseDetailSyllabusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailSyllabusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout()
    }

    private fun tabLayout() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }
}
