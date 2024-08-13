package com.comfest.instructor.ui.sylabus

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.comfest.instructor.data.source.remote.response.Syllabus
import com.comfest.instructor.data.source.remote.response.SyllabusDetail
import com.comfest.instructor.domain.model.RequestCreateAssignment
import com.comfest.seatudy.R
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityAssignmentSyllabusInstructorBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AssignmentSyllabusInstructorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssignmentSyllabusInstructorBinding
    private lateinit var syllabusViewModel: SyllabusViewModel


    private var syllabusId: Int? = null
    private var tokenUser: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAssignmentSyllabusInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val syllabus = intent.getParcelableExtra<SyllabusDetail>("syllabus")
        syllabusId = syllabus?.syllabusID

        syllabusViewModel = ViewModelProvider(this)[SyllabusViewModel::class.java]
        syllabusViewModel.getToken().observe(this){
            tokenUser = it
        }

        binding.btnAddAssignment.setOnClickListener {
            addAssignment()
        }
    }



        private fun addAssignment() {
        binding.apply {
            val titleAssignment = edTitleAssignment.text.toString()
            val descAssignment = edDescAssignment.text.toString()
            val time = edDueAssignment.text.toString()
            if (titleAssignment.isEmpty() || descAssignment.isEmpty() || time.isEmpty() ) {
                Toast.makeText(this@AssignmentSyllabusInstructorActivity, "Please fill in all the fields add assignment", Toast.LENGTH_SHORT).show()
            }

            val requestCreateAssignment = RequestCreateAssignment(
                title = titleAssignment,
                description = descAssignment,
                maximumTime = time.toInt()
            )

            syllabusViewModel.createAssignment(syllabusId!!, tokenUser!!, requestCreateAssignment).observe(this@AssignmentSyllabusInstructorActivity) {
                when(it) {
                    is Resource.Loading -> {
                        Toast.makeText(this@AssignmentSyllabusInstructorActivity, "Creating assignment...", Toast.LENGTH_SHORT).show()
                        Log.d("AssignmentInstructorActivity", "SYLLABUS_ID: $syllabusId")
                        Log.d("AssignmentInstructorActivity", "TOKEN_USER: $tokenUser")
                    }

                    is Resource.Success -> {
                        Toast.makeText(this@AssignmentSyllabusInstructorActivity, "Assignment created successfully", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Error -> {
                        Toast.makeText(this@AssignmentSyllabusInstructorActivity, "Failed to create assignment: ${it.message}", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(
                            this@AssignmentSyllabusInstructorActivity,
                            "Recheck your input assignment",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

}