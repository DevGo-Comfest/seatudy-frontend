package com.comfest.instructor.ui.sylabus

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.data.source.remote.response.SyllabusDetail
import com.comfest.instructor.domain.model.RequestUpdateSyllabus
import com.comfest.instructor.ui.sylabus.adapter.SyllabusInstructorAdapter
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityUpdateSyllabusBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateSyllabusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateSyllabusBinding
    private lateinit var syllabusViewModel: SyllabusViewModel
    private lateinit var syllabusAdapter: SyllabusInstructorAdapter


    private var tokenUser: String? = null
    private var syllabusId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateSyllabusBinding.inflate(layoutInflater)
        setContentView(binding.root)


        syllabusViewModel = ViewModelProvider(this)[SyllabusViewModel::class.java]

        syllabusViewModel.getToken().observe(this) {
            tokenUser = it
        }


        val course = intent.getParcelableExtra<Course>("course_detail")

        val syllabus = intent.getParcelableExtra<SyllabusDetail>("syllabus")
        syllabusId = syllabus?.syllabusID

        syllabus.let { item ->
            binding.apply {
                edTitleSylabus.setText(item?.title)
                edDescSylabus.setText(item?.description)
            }
        }






        binding.btnUpdateSyllabus.setOnClickListener {
            binding.apply {

                val titleSyllabus = edTitleSylabus.text.toString()
                val descSyllabus = edDescSylabus.text.toString()


                val requestUpdateSyllabus = RequestUpdateSyllabus(
                    title = titleSyllabus,
                    description = descSyllabus,
                )

                syllabusViewModel.updateSyllabus(syllabusId!!, tokenUser!!, requestUpdateSyllabus).observe(this@UpdateSyllabusActivity){
                    when(it) {
                        is Resource.Loading -> {
                        }

                        is Resource.Success -> {
                            Toast.makeText(this@UpdateSyllabusActivity, "syllabus updated successfully", Toast.LENGTH_SHORT).show()
                            // Optionally, you can navigate back or clear the form
                            finish() // Or any other action like navigating to another activity
                            loadSyllabus(course!!.CourseID, tokenUser!!)
                        }

                        is Resource.Error -> {
                            Toast.makeText(this@UpdateSyllabusActivity, "Failed to update syllabus: ${it.message}", Toast.LENGTH_SHORT).show()
                        }

                        else -> {
                            Toast.makeText(
                                this@UpdateSyllabusActivity,
                                "Recheck your input syllabus update",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

    }



    private fun loadSyllabus(courseId: Int, token: String) {
        syllabusViewModel.getDetailCourse(courseId, token).observe(this@UpdateSyllabusActivity) {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    it.data?.body()?.course?.syllabuses?.let { syllabusDetail ->
                        syllabusAdapter.setSyllabus(syllabusDetail)
                    }
                }
                is Resource.Error -> {
                    // Handle the error
                    Toast.makeText(this@UpdateSyllabusActivity, "Error get syllabuses...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}