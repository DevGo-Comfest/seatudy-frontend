package com.comfest.instructor.ui.assignment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.source.remote.response.SyllabusDetail
import com.comfest.instructor.ui.assignment.adapter.AssignmentInstructorAdapter
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityAssignmentInstructorBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AssignmentInstructorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssignmentInstructorBinding
    private lateinit var assignmentInstructorAdapter: AssignmentInstructorAdapter
    private lateinit var viewModel: AssignViewModel

    private var tokenUser: String = ""
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAssignmentInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val syllabus = intent.getParcelableExtra<SyllabusDetail>("syllabus")

        viewModel = ViewModelProvider(this)[AssignViewModel::class.java]

        setupRecyclerView()

        viewModel.getToken().observe(this) {token ->

            viewModel.getSubmissionUser(syllabus!!.syllabusID, token).observe(this) {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        assignmentInstructorAdapter.setAssignment(it.data?.body()?.submissions!!)
                        val status = it.data.body()?.submissions!!.map {item ->
                            item.status
                        }
                        Log.d("AssignmentInstructorActivity", "STATUS: $status")
                        Toast.makeText(this@AssignmentInstructorActivity, "Success get submision", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        Log.d("AssignmentInstructorActivity", "ERROR: ${it.message}")
                        Toast.makeText(this@AssignmentInstructorActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            tokenUser = token
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }


    private fun setupRecyclerView() {
        assignmentInstructorAdapter = AssignmentInstructorAdapter()
        binding.rvListAssignment.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = assignmentInstructorAdapter
        }
    }
}