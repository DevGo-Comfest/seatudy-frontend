package com.comfest.seatudy.ui.dashboard.detailpersyllabus

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityCourseDetailPerSyllabusBinding
import com.comfest.seatudy.domain.model.DataAssignment
import com.comfest.seatudy.ui.dashboard.detailsubmission.DetailAssignmentActivity
import com.comfest.seatudy.utils.DateFormat.formatDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseDetailPerSyllabusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseDetailPerSyllabusBinding
    private lateinit var courseDetailPerSyllabusViewModel: CourseDetailPerSyllabusViewModel
    private lateinit var adapterMaterial: AdapterMaterial

    private var currentCourseIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailPerSyllabusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        courseDetailPerSyllabusViewModel =
            ViewModelProvider(this)[CourseDetailPerSyllabusViewModel::class.java]

        val idSyllabus = intent.getStringExtra("ID")?.toInt()

        if (idSyllabus != null) {
            currentCourseIndex = idSyllabus
        }
        data(currentCourseIndex)

        val size = intent.getStringExtra("SIZE")!!.toInt() + 1
        val title = intent.getStringExtra("TITLE")
        val description = intent.getStringExtra("DESCRIPTION")
        binding.tvSyllabus.text = title
        binding.tvDescriptionTitle.text = description

        binding.btnNextCourse.setOnClickListener {
            if (currentCourseIndex < size - 1) {
                currentCourseIndex++
                data(currentCourseIndex)

                // Next Send Progress per syllabus

            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun data(id: Int) {
        courseDetailPerSyllabusViewModel.getSyllabus(id.toString()).observe(this) { data ->
            when (data) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    val value = data.data?.body()?.syllabus
                    if (value != null) {
                        adapterMaterial = AdapterMaterial(value.materials)
                        binding.rvMaterial.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                        binding.rvMaterial.adapter = adapterMaterial

                        binding.tvSyllabus.text = value.title
                        binding.tvDescriptionTitle.text = value.description

                        value.assignments.forEach { dataAssignment ->
                            binding.tvNameSubmission.text = dataAssignment.title
                            binding.tvDay.text = dataAssignment.maximumTime.toString() + " Day"

                            courseDetailPerSyllabusViewModel.getToken().observe(this) { token ->
                                val idSyllabus = intent.getStringExtra("ID").toString()
                                binding.cardAssignment.setOnClickListener {
                                    courseDetailPerSyllabusViewModel.sendOpenAssignment(DataAssignment(idSyllabus.toInt()), "Bearer $token").observe(this) { data ->
                                        when (data) {
                                            is Resource.Loading -> {

                                            }

                                            is Resource.Success -> {
                                                Toast.makeText(
                                                    this,
                                                    "Succes Open Submission",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }

                                            is Resource.Error -> {

                                            }
                                        }
                                    }
                                    val intent =
                                        Intent(
                                            this,
                                            DetailAssignmentActivity::class.java
                                        ).apply {
                                            putExtra("ID", dataAssignment.syllabusID.toString())
                                            putExtra("TITLE", dataAssignment.title)
                                            putExtra("TIME", dataAssignment.maximumTime.toString())
                                            putExtra("DESCRIPTION", dataAssignment.description)
                                        }
                                    startActivity(intent)
                                }

                                courseDetailPerSyllabusViewModel.getUserAssignment(idSyllabus, "Bearer $token").observe(this){ dataSubmit ->
                                    when(dataSubmit){
                                        is Resource.Loading -> {

                                        }
                                        is Resource.Success -> {
                                            val dataUserSubmit = dataSubmit.data?.body()
                                            if (dataUserSubmit != null){
                                                binding.cardAssignment.isEnabled = false
                                                binding.tvNameSubmission.text = "Review"
                                                binding.tvDay.text = formatDate(dataUserSubmit.dueDate)
                                            }
                                        }
                                        is Resource.Error -> {

                                        }
                                    }
                                }
                            }

                        }
                    }

                }

                is Resource.Error -> {

                }
            }
        }
    }
}