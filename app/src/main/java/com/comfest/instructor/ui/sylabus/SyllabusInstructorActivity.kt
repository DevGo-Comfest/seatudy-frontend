package com.comfest.instructor.ui.sylabus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.data.source.remote.response.SyllabusDetail
import com.comfest.instructor.domain.model.RequestCreateAssignment
import com.comfest.instructor.domain.model.RequestCreateSyllabus
import com.comfest.instructor.ui.sylabus.adapter.SyllabusInstructorAdapter
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivitySyllabusInstructorBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SyllabusInstructorActivity : AppCompatActivity(), SyllabusInstructorAdapter.OnItemClickListener {

    private lateinit var binding: ActivitySyllabusInstructorBinding
    private lateinit var syllabusAdapter: SyllabusInstructorAdapter
    private lateinit var syllabusViewModel: SyllabusViewModel

    private var syllabusId: Int? = null
    private var tokenUser: String? = null
    private var courseId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySyllabusInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val course = intent.getParcelableExtra<Course>("course_detail")
        courseId = course!!.CourseID

        syllabusViewModel = ViewModelProvider(this)[SyllabusViewModel::class.java]


        syllabusAdapter = SyllabusInstructorAdapter(this)
        binding.rvList.adapter = syllabusAdapter
        binding.rvList.layoutManager = LinearLayoutManager(this)


        syllabusViewModel.getToken().observe(this){token ->
            loadSyllabus(courseId, token)
            tokenUser = token
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnAddSyllabus.setOnClickListener {
            addSyllabus()
        }

        binding.btnAddAssignment.setOnClickListener {
            addAssignment()
        }

//        setupRecyclerView()
//        loadedSyllabus()
    }

    private fun addAssignment() {
        binding.apply {
            val titleAssignment = edTitleAssignment.text.toString()
            val descAssignment = edDescAssignment.text.toString()
            val time = edDueAssignment.text.toString()
            if (titleAssignment.isEmpty() || descAssignment.isEmpty() || time.isEmpty() ) {
                Toast.makeText(this@SyllabusInstructorActivity, "Please fill in all the fields add assignment", Toast.LENGTH_SHORT).show()
            }

            val requestCreateAssignment = RequestCreateAssignment(
                title = titleAssignment,
                description = descAssignment,
                maximumTime = time.toInt()
            )

            syllabusViewModel.createAssignment(syllabusId!!, tokenUser!!, requestCreateAssignment).observe(this@SyllabusInstructorActivity) {
                when(it) {
                    is Resource.Loading -> {
                        Toast.makeText(this@SyllabusInstructorActivity, "Creating assignment...", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        Toast.makeText(this@SyllabusInstructorActivity, "Assignment created successfully", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Error -> {
                        Toast.makeText(this@SyllabusInstructorActivity, "Failed to create assignment: ${it.message}", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(
                            this@SyllabusInstructorActivity,
                            "Recheck your input assignment",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun addSyllabus() {
        binding.apply {
            val titleSyllabus = edTitleSylabus.text.toString()
            val descSyllabus = edDescSylabus.text.toString()

            if (titleSyllabus.isEmpty() || descSyllabus.isEmpty()) {
                Toast.makeText(this@SyllabusInstructorActivity, "Please fill in all the fields add syllabus", Toast.LENGTH_SHORT).show()
            }

            val requestCreateSyllabus = RequestCreateSyllabus(
                title = titleSyllabus,
                description = descSyllabus,
                courseId = courseId!!
            )

            syllabusViewModel.createSyllabus(tokenUser!!, requestCreateSyllabus).observe(this@SyllabusInstructorActivity) {
                when(it) {
                    is Resource.Loading -> {
                        Toast.makeText(this@SyllabusInstructorActivity, "Creating syllabus...", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        Toast.makeText(this@SyllabusInstructorActivity, "Syllabus created successfully", Toast.LENGTH_SHORT).show()
                        syllabusId = it.data?.body()?.syllabus?.SyllabusID
                        loadSyllabus(courseId, tokenUser!!)
                    }

                    is Resource.Error -> {
                        Toast.makeText(this@SyllabusInstructorActivity, "Failed to create syllabus: ${it.message}", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(
                            this@SyllabusInstructorActivity,
                            "Recheck your input syllabus",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
    override fun onItemClick(syllabus: SyllabusDetail) {
        TODO("Not yet implemented")
    }

    override fun onUpdateClick(syllabus: SyllabusDetail) {
        TODO("Not yet implemented")
    }

    private fun loadSyllabus(courseId: Int, token: String) {
        syllabusViewModel.getDetailCourse(courseId, token).observe(this@SyllabusInstructorActivity) {
            when (it) {
                is Resource.Loading -> {
                    // Show loading indicator
                    Toast.makeText(this@SyllabusInstructorActivity, "Loading get syllabuses...", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    it.data?.body()?.course?.syllabuses?.let { syllabusDetail ->
                        syllabusAdapter.setSyllabus(syllabusDetail)
                    }

                    Toast.makeText(this@SyllabusInstructorActivity, "Success get syllabuses...", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    // Handle the error
                    Toast.makeText(this@SyllabusInstructorActivity, "Error get syllabuses...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}