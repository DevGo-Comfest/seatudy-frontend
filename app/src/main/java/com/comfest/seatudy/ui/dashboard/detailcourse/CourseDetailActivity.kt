package com.comfest.seatudy.ui.dashboard.detailcourse

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityCourseDetailBinding
import com.comfest.seatudy.ui.dashboard.detailsyllabus.CourseDetailSyllabusActivity
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseDetailBinding
    private lateinit var courseDetailViewModel: CourseDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        courseDetailViewModel = ViewModelProvider(this)[CourseDetailViewModel::class.java]

        binding.btnStartCourse.setOnClickListener {
            startActivity(
                Intent(
                    this@CourseDetailActivity,
                    CourseDetailSyllabusActivity::class.java
                )
            )
        }

        tabLayout()
        toDetailCourse()
    }

    @SuppressLint("SetTextI18n")
    private fun toDetailCourse() {
        val courseID = intent.getStringExtra("TITLE").toString()
        courseDetailViewModel.getCoursesWithID(courseID).observe(this) {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    val data = it.data?.body()?.courses
                    Log.d("CEK", "$data")
                    if (data != null) {
                        binding.apply {
                            Log.d("CEK", data.title)
                            Glide.with(this@CourseDetailActivity)
                                .load(data.imageURL)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .centerCrop()
                                .into(binding.imgCourse)
                            tvNameCourse.text = title
                            tvPrice.text = "Rp. ${data.price}"
                            tvDescriptionSyllabus.text = data.description
                        }
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

    private fun tabLayout() {
        val courseID = intent.getStringExtra("TITLE").toString()
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }
}