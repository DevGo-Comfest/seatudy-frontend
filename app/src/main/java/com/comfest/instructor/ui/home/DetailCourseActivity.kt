package com.comfest.instructor.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.ui.assignment.AddInstructorActivity
import com.comfest.instructor.ui.assignment.AssignmentInstructorActivity
import com.comfest.instructor.ui.discussion.DiscussionInstructorActivity
import com.comfest.instructor.ui.sylabus.SyllabusInstructorActivity
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityDetailCourseBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCourseBinding
    private lateinit var homeInstructorViewModel: HomeInstructorViewModel
    private var tokenUser: String? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeInstructorViewModel = ViewModelProvider(this)[HomeInstructorViewModel::class.java]

        homeInstructorViewModel.getToken().observe(this){
            tokenUser = it
        }



        val course = intent.getParcelableExtra<Course>("course")

        course.let {
            Glide.with(this)
                .load(it?.ImageURL)
                .into(binding.ivDetailCourse)

            binding.tvTitle.text = it?.Title
            binding.tvDesc.text = it?.Description
            binding.ratingBar.rating = it?.Rating!!.toFloat()
        }

        if (course?.Status == "active") {
            binding.btnActivated.isEnabled = false
            binding.btnActivated.text = "Course Active"
        } else if (course?.Status == "inactive") {
            binding.btnActivated.isEnabled = true
        }

        binding.btnActivated.setOnClickListener {
            homeInstructorViewModel.activatedCourse(course!!.CourseID, tokenUser!!).observe(this){
                when(it) {
                    is Resource.Loading -> {
                        Toast.makeText(this@DetailCourseActivity, "Loading Activated Course", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        Toast.makeText(this@DetailCourseActivity, "Activated course successfully", Toast.LENGTH_SHORT).show()
                        binding.btnActivated.text = "Course Active"
                        binding.btnActivated.isEnabled = false
                    }

                    is Resource.Error -> {
                        Toast.makeText(this@DetailCourseActivity, "Activated failed", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(
                            this@DetailCourseActivity,
                            "Internal Server Error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.btnSyllabus.setOnClickListener {
            val intent = Intent(this, SyllabusInstructorActivity::class.java)
            intent.putExtra("course_detail", course)
            startActivity(intent)
        }

        binding.btnAssignment.setOnClickListener {
            val intent = Intent(this, AssignmentInstructorActivity::class.java)
            startActivity(intent)
        }
        
        binding.btnAssignInstructor.setOnClickListener { 
            val intent = Intent(this, AddInstructorActivity::class.java)
            intent.putExtra("course", course)
            startActivity(intent)
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnDiscussion.setOnClickListener {
            val intent = Intent(this, DiscussionInstructorActivity::class.java)
            intent.putExtra("course", course)
            startActivity(intent)
        }
    }
}