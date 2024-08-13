package com.comfest.seatudy.ui.dashboard.detailcourse

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityCourseDetailBinding
import com.comfest.seatudy.ui.cart.payment.PaymentActivity
import com.comfest.seatudy.ui.dashboard.detailsyllabus.CourseDetailSyllabusActivity
import com.google.android.material.tabs.TabLayoutMediator
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

        //tabLayout()
        toDetailCourse()
    }

    @SuppressLint("SetTextI18n")
    private fun toDetailCourse() {
        val courseID = intent.getStringExtra("TITLE").toString()
        courseDetailViewModel.getCoursesWithID(courseID).observe(this) { dataID ->
            when (dataID) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    val data = dataID.data?.body()?.courses
                    if (data != null) {
                        courseDetailViewModel.getToken().observe(this) { token ->
                            courseDetailViewModel.getEnrolledCourse("Bearer $token")
                                .observe(this) { value ->
                                    when (value) {
                                        is Resource.Loading -> {

                                        }

                                        is Resource.Success -> {
                                            val isCourseEnrolled = value.data?.body()?.courses?.any { it.courseID == courseID.toInt() } ?: false

                                            if (isCourseEnrolled) {
                                                binding.btnStartCourse.text = "Start Course"

                                                // Update Start Course / is_locked


                                                binding.btnStartCourse.setOnClickListener {
                                                    val intent = Intent(
                                                        this@CourseDetailActivity,
                                                        CourseDetailSyllabusActivity::class.java
                                                    ).apply {
                                                        putExtra("COURSEID", courseID)
                                                        putExtra("TITLE", data.title)
                                                        putExtra("IMG", data.imageURL)
                                                        putExtra("PRICE", data.price)
                                                    }
                                                    startActivity(intent)
                                                }
                                            } else {
                                                binding.btnStartCourse.text = "Price"
                                                binding.btnStartCourse.setOnClickListener {
                                                    val intent = Intent(
                                                        this@CourseDetailActivity,
                                                        PaymentActivity::class.java
                                                    ).apply {
                                                        putExtra("COURSEID", courseID)
                                                        putExtra("TITLE", data.title)
                                                        putExtra("IMG", data.imageURL)
                                                        putExtra("PRICE", data.price.toString())
                                                    }
                                                    startActivity(intent)
                                                }
                                            }
                                        }

                                        is Resource.Error -> {
                                            Toast.makeText(
                                                this,
                                                "${dataID.message}",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        }
                                    }
                                }
                        }

                        binding.apply {
                            Glide.with(this@CourseDetailActivity)
                                .load(data.imageURL)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .centerCrop()
                                .into(binding.imgCourse)

                            tvSumSubSyllabus.text = "${data.syllabuses.size} Syllabus"
                            tvNameCourse.text = data.title
                            tvCategory.text = data.category
                            tvPrice.text = "Rp. ${data.price}"
                            tvDescriptionSyllabus.text = data.description
                            tabLayout(courseID)
                        }
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(this, "${dataID.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

    private fun tabLayout(course: String) {
        val sectionsPagerAdapter = SectionsPagerAdapterDetail(this, course)
        binding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
}