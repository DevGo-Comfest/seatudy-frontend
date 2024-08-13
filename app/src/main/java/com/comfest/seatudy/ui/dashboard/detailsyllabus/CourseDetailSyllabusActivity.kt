package com.comfest.seatudy.ui.dashboard.detailsyllabus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.comfest.seatudy.databinding.ActivityCourseDetailSyllabusBinding
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseDetailSyllabusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseDetailSyllabusBinding
    private lateinit var courseDetailViewModel: CourseDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailSyllabusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        courseDetailViewModel = ViewModelProvider(this)[CourseDetailViewModel::class.java]

        syllabus()
    }

    private fun syllabus() {
        val courseID = intent.getStringExtra("COURSEID").toString()
        val title = intent.getStringExtra("TITLE").toString()
        val img = intent.getStringExtra("IMG").toString()

        binding.tvNameCourse.text = title

        Glide.with(this)
            .load(img)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(binding.imgCourse)

        tabLayout(courseID)
    }

    private fun tabLayout(course: String) {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, course)
        binding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text =
                resources.getString(TAB_TITLES[position])
        }.attach()

    }
}
