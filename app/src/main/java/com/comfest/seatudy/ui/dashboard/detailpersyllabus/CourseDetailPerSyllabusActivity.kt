package com.comfest.seatudy.ui.dashboard.detailpersyllabus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityCourseDetailPerSyllabusBinding
import com.comfest.seatudy.ui.dashboard.detailsubmission.DetailSubmissionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseDetailPerSyllabusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseDetailPerSyllabusBinding
    private lateinit var courseDetailPerSyllabusViewModel: CourseDetailPerSyllabusViewModel
    private lateinit var adapterMaterial: AdapterMaterial

    private var currentCourseIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailPerSyllabusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        courseDetailPerSyllabusViewModel =
            ViewModelProvider(this)[CourseDetailPerSyllabusViewModel::class.java]

        data(currentCourseIndex)

        // PerSyllabus
        val size = intent.getStringExtra("SIZE")!!.toInt() + 1
        val title = intent.getStringExtra("TITLE")
        val description = intent.getStringExtra("DESCRIPTION")
        binding.tvSyllabus.text = title
        binding.tvDescriptionTitle.text = description
        binding.btnNextCourse.setOnClickListener {
            Toast.makeText(this, "$currentCourseIndex", Toast.LENGTH_SHORT).show()
            if (currentCourseIndex < size - 1) {
                currentCourseIndex++
                data(currentCourseIndex)
            }else{
                startActivity(Intent(this, DetailSubmissionActivity::class.java))
            }
        }
    }

    private fun data(id: Int) {
        courseDetailPerSyllabusViewModel.getSyllabus(id.toString()).observe(this) { data ->
            when (data) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    val value = data.data?.body()?.syllabus
                    if (value != null) {
                        adapterMaterial = AdapterMaterial(value.materials)
                        binding.rvMaterial.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                        binding.rvMaterial.adapter = adapterMaterial
                    }

                }

                is Resource.Error -> {

                }
            }
        }

    }
}