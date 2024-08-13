package com.comfest.instructor.ui.sylabus

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.comfest.instructor.data.source.remote.response.AssignmentSyllabus
import com.comfest.instructor.data.source.remote.response.SyllabusDetail
import com.comfest.instructor.domain.model.RequestCreateAssignment
import com.comfest.instructor.domain.model.RequestCreateSyllabus
import com.comfest.instructor.ui.sylabus.adapter.AssignmentSyllabusAdapter
import com.comfest.seatudy.R
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityUpdateAssignmentSyllabusBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateAssignmentSyllabusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateAssignmentSyllabusBinding
    private lateinit var syllabusViewModel: SyllabusViewModel
    private lateinit var assignmentAdapter: AssignmentSyllabusAdapter


    private var tokenUser: String? = null
    private var assignmentId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateAssignmentSyllabusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        syllabusViewModel = ViewModelProvider(this)[SyllabusViewModel::class.java]

        syllabusViewModel.getToken().observe(this) {
            tokenUser = it
        }

        val syllabus = intent.getParcelableExtra<SyllabusDetail>("syllabus")

        val assignment = intent.getParcelableExtra<AssignmentSyllabus>("assignment")
        assignmentId = assignment?.assignmentID

        assignment.let { item ->
            binding.apply {
                edTitleAssignment.setText(item?.title)
                edDescAssignment.setText(item?.description)
                edDueAssignment.setText(item?.maximumTime.toString())
            }
        }


        binding.btnUpdateAssignment.setOnClickListener {
            binding.apply {
                val titleAssignment = edTitleAssignment.text.toString()
                val descAssignment = edDescAssignment.text.toString()
                val dueAssignment = edDueAssignment.text.toString()

                val requestUpdateSyllabus = RequestCreateAssignment(
                    title = titleAssignment,
                    description = descAssignment,
                    maximumTime = dueAssignment.toInt()
                )

                syllabusViewModel.updateAssignment(assignmentId!!, tokenUser!!, requestUpdateSyllabus).observe(this@UpdateAssignmentSyllabusActivity) {
                    when(it) {
                        is Resource.Loading -> {
                        }

                        is Resource.Success -> {
                            Toast.makeText(this@UpdateAssignmentSyllabusActivity, "assignment updated successfully", Toast.LENGTH_SHORT).show()
                            finish()
                            loadAssignment(syllabus!!.syllabusID, tokenUser!!)
                        }

                        is Resource.Error -> {
                            Toast.makeText(this@UpdateAssignmentSyllabusActivity, "Failed to update assignment: ${it.message}", Toast.LENGTH_SHORT).show()
                        }

                        else -> {
                            Toast.makeText(
                                this@UpdateAssignmentSyllabusActivity,
                                "Recheck your input assignment update",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }



    private fun loadAssignment(syllabusId: Int, token: String) {
        syllabusViewModel.getAssignmentBySyllabusId(syllabusId, token).observe(this@UpdateAssignmentSyllabusActivity) {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    it.data?.body()?.syllabus?.assignments?.let { assignment ->
                        assignmentAdapter.setAssignment(assignment)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(this@UpdateAssignmentSyllabusActivity, "Error get assignment...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}