package com.comfest.instructor.ui.assignment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.dummy.AssignmentInstructor
import com.comfest.instructor.ui.assignment.adapter.AssignmentInstructorAdapter
import com.comfest.seatudy.databinding.ActivityAssignmentInstructorBinding

class AssignmentInstructorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssignmentInstructorBinding
    private lateinit var assignmentInstructorAdapter: AssignmentInstructorAdapter
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAssignmentInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }


        setupRecyclerView()
        loadedAssignment()
    }


    private fun setupRecyclerView() {
        assignmentInstructorAdapter = AssignmentInstructorAdapter()
        binding.rvListAssignment.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = assignmentInstructorAdapter
        }
    }



    private fun loadedAssignment() {
        val nameStudent = "Joko Anwar"
        val linkAttachment = "https:test123"
        val dateSubmit = "10-11-2024"
        val sampleAssignment = listOf(
            AssignmentInstructor(nameStudent, linkAttachment, dateSubmit),
            AssignmentInstructor(nameStudent, linkAttachment, dateSubmit),
            AssignmentInstructor(nameStudent, linkAttachment, dateSubmit),
            AssignmentInstructor(nameStudent, linkAttachment, dateSubmit),
            AssignmentInstructor(nameStudent, linkAttachment, dateSubmit),
        )

        assignmentInstructorAdapter.setAssignment(sampleAssignment)
    }
}