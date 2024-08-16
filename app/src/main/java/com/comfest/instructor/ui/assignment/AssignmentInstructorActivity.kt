package com.comfest.instructor.ui.assignment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.data.source.remote.response.Submission
import com.comfest.instructor.data.source.remote.response.SyllabusDetail
import com.comfest.instructor.domain.model.RequestAddGradeSubmission
import com.comfest.instructor.ui.assignment.adapter.AssignmentInstructorAdapter
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityAssignmentInstructorBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AssignmentInstructorActivity : AppCompatActivity(), AssignmentInstructorAdapter.OnItemClickListener {

    private lateinit var binding: ActivityAssignmentInstructorBinding
    private lateinit var assignmentInstructorAdapter: AssignmentInstructorAdapter
    private lateinit var viewModel: AssignViewModel

    private var tokenUser: String = ""
    private var syllabusIdLoad: Int = 0
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAssignmentInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val course = intent.getParcelableExtra<Course>("course")
        val syllabus = intent.getParcelableExtra<SyllabusDetail>("syllabus")
        syllabusIdLoad = syllabus?.syllabusID!!

        Glide.with(this)
            .load(course?.ImageURL)
            .centerCrop()
            .into(binding.ivDetailCourse)

        binding.tvTitle.text = course?.Title
        binding.tvDesc.text = course?.Description
        binding.ratingBar.rating = course?.Rating!!.toFloat()

        viewModel = ViewModelProvider(this)[AssignViewModel::class.java]

        setupRecyclerView()

        viewModel.getToken().observe(this) {token ->
            loadSubmission(syllabus.syllabusID, token)
            tokenUser = token
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun loadSubmission(syllabusId: Int, token: String) {
        viewModel.getSubmissionUser(syllabusId, token).observe(this) {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {

                    val submission = it.data?.body()?.submissions

                    if (submission.isNullOrEmpty()) {
                        binding.ivNoData.visibility = View.VISIBLE
                        binding.tvNoData.visibility = View.VISIBLE
                        binding.rvListAssignment.visibility = View.GONE

                    } else {
                        binding.ivNoData.visibility = View.GONE
                        binding.tvNoData.visibility = View.GONE
                        binding.rvListAssignment.visibility = View.VISIBLE
                        assignmentInstructorAdapter.setAssignment(submission)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(this@AssignmentInstructorActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun setupRecyclerView() {
        assignmentInstructorAdapter = AssignmentInstructorAdapter(this)
        binding.rvListAssignment.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = assignmentInstructorAdapter
        }
    }

    override fun onAddGradeClick(submission: Submission, grade: Int) {
        val requestAddGradeSubmission = RequestAddGradeSubmission(
            gradeScore = grade
        )
        viewModel.addGrade(submission.submissionID, tokenUser, requestAddGradeSubmission).observe(this){
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    loadSubmission(syllabusIdLoad, tokenUser)
                    Toast.makeText(this@AssignmentInstructorActivity, "Success add grade", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    Toast.makeText(this@AssignmentInstructorActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}