package com.comfest.instructor.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.comfest.instructor.ui.assignment.AddInstructorActivity
import com.comfest.instructor.ui.assignment.AssignmentInstructorActivity
import com.comfest.instructor.ui.discussion.DiscussionInstructorActivity
import com.comfest.instructor.ui.sylabus.SyllabusInstructorActivity
import com.comfest.seatudy.databinding.ActivityDetailCourseBinding

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCourseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSyllabus.setOnClickListener {
            val intent = Intent(this, SyllabusInstructorActivity::class.java)
            startActivity(intent)
        }

        binding.btnAssignment.setOnClickListener {
            val intent = Intent(this, AssignmentInstructorActivity::class.java)
            startActivity(intent)
        }
        
        binding.btnAssignInstructor.setOnClickListener { 
            val intent = Intent(this, AddInstructorActivity::class.java)
            startActivity(intent)
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnDiscussion.setOnClickListener {
            val intent = Intent(this, DiscussionInstructorActivity::class.java)
            startActivity(intent)
        }
    }
}