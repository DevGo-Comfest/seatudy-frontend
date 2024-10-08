@file:Suppress("DEPRECATION")

package com.comfest.instructor.ui.assignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.domain.model.RequestAssignInstructor
import com.comfest.instructor.ui.assignment.adapter.AddInstructorAdapter
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityAddInstructorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddInstructorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddInstructorBinding
    private lateinit var addInstructorAdapter: AddInstructorAdapter
    private lateinit var assignViewModel: AssignViewModel

    private var tokenUser: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assignViewModel = ViewModelProvider(this)[AssignViewModel::class.java]
        assignViewModel.getToken().observe(this){
            tokenUser = it
        }

        val course = intent.getParcelableExtra<Course>("course")

        setupRecyclerView()

        assignViewModel.getInstructor().observe(this) {
            when(it) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    addInstructorAdapter.setInstructor(it.data!!.body()!!.instructors)
                }

                is Resource.Error -> {
                    Toast.makeText(this@AddInstructorActivity, "Failed to create syllabus: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnAddInstructor.setOnClickListener {
            val selectedItems = addInstructorAdapter.getSelectedItem()
            val requestAssignInstructor = RequestAssignInstructor(
                selectedItems.map { it.userID }
            )
            assignViewModel.assignInstructor(course!!.CourseID, tokenUser, requestAssignInstructor ).observe(this){
                when(it) {
                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {
                        Toast.makeText(this@AddInstructorActivity, "Assign instructor successfully", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Error -> {
                        Toast.makeText(this@AddInstructorActivity, "Failed to assign instructor: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    private fun setupRecyclerView() {
        addInstructorAdapter = AddInstructorAdapter()
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = addInstructorAdapter
        }
    }
}