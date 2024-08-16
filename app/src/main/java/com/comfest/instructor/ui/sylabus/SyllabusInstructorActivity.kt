package com.comfest.instructor.ui.sylabus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.data.source.remote.response.SyllabusDetail
import com.comfest.instructor.domain.model.RequestCreateSyllabus
import com.comfest.instructor.ui.assignment.AssignmentInstructorActivity
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
    private var course: Course? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySyllabusInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        course = intent.getParcelableExtra("course_detail")
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
                courseId = courseId
            )

            syllabusViewModel.createSyllabus(tokenUser!!, requestCreateSyllabus).observe(this@SyllabusInstructorActivity) {
                when(it) {
                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {
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
        val intent = Intent(this, AssignmentSyllabusInstructorActivity::class.java)
        intent.putExtra("syllabus", syllabus)
        startActivity(intent)
    }

    override fun onUpdateClick(syllabus: SyllabusDetail) {
        val intent = Intent(this, UpdateSyllabusActivity::class.java)
        intent.putExtra("syllabus", syllabus)
        startActivity(intent)
    }

    override fun onAddAssignmentClick(syllabus: SyllabusDetail) {
        val intent = Intent(this, AssignmentSyllabusInstructorActivity::class.java)
        intent.putExtra("syllabus", syllabus)

        intent.putExtra("course_detail", course)
        startActivity(intent)
    }

    override fun onAddSyllabusMaterialClick(syllabus: SyllabusDetail) {
        val intent = Intent(this, SyllabusMaterialInstructorActivity::class.java)
        intent.putExtra("syllabus", syllabus)
        startActivity(intent)
    }

    override fun onDeleteClick(syllabus: SyllabusDetail) {
        syllabusViewModel.deleteSyllabus(syllabus.syllabusID, tokenUser!!).observe(this){
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    loadSyllabus(courseId, tokenUser!!)
                    Toast.makeText(this@SyllabusInstructorActivity, "Success delete syllabus", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    Toast.makeText(this@SyllabusInstructorActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onSubmissionUser(syllabus: SyllabusDetail) {
        val intent = Intent(this, AssignmentInstructorActivity::class.java)
        intent.putExtra("syllabus", syllabus)

        intent.putExtra("course", course)
        startActivity(intent)
    }

    private fun loadSyllabus(courseId: Int, token: String) {
        syllabusViewModel.getDetailCourse(courseId, token).observe(this@SyllabusInstructorActivity) {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val syllabusDetail = it.data?.body()?.course?.syllabuses
                    if (syllabusDetail.isNullOrEmpty()) {
                        binding.ivNoData.visibility = View.VISIBLE
                        binding.tvNoData.visibility = View.VISIBLE
                        binding.rvList.visibility = View.GONE
                    } else {
                        binding.ivNoData.visibility = View.GONE
                        binding.tvNoData.visibility = View.GONE
                        binding.rvList.visibility = View.VISIBLE
                        syllabusAdapter.setSyllabus(syllabusDetail)
                    }
                }
                is Resource.Error -> {
                    // Handle the error
                    Toast.makeText(this@SyllabusInstructorActivity, "Error get syllabuses...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}