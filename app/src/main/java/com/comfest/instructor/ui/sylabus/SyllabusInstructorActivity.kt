package com.comfest.instructor.ui.sylabus

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.dummy.SyllabusDataInstructor
import com.comfest.instructor.ui.sylabus.adapter.SyllabusInstructorAdapter
import com.comfest.seatudy.databinding.ActivitySyllabusInstructorBinding

class SyllabusInstructorActivity : AppCompatActivity(), SyllabusInstructorAdapter.OnItemClickListener {

    private lateinit var binding: ActivitySyllabusInstructorBinding
    private lateinit var syllabusAdapter: SyllabusInstructorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySyllabusInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        setupRecyclerView()
        loadedSyllabus()
    }


    private fun setupRecyclerView() {
        syllabusAdapter = SyllabusInstructorAdapter(this)
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = syllabusAdapter
        }
    }

    private fun loadedSyllabus() {
        val titleDummy = "Lorem ipsum dolor sit amet"
        val descDummy = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        val sampleSyllabus = listOf(
            SyllabusDataInstructor(titleDummy, descDummy, titleDummy, 4),
            SyllabusDataInstructor(titleDummy, descDummy, titleDummy, 3),
            SyllabusDataInstructor(titleDummy, descDummy, titleDummy, 1),
            SyllabusDataInstructor(titleDummy, descDummy, titleDummy, 5),
        )
        syllabusAdapter.setSyllabus(sampleSyllabus)
    }

    override fun onItemClick(syllabus: SyllabusDataInstructor) {
        val intent = Intent(this, SyllabusMaterialInstructorActivity::class.java)
        startActivity(intent)
    }

    override fun onUpdateClick(syllabus: SyllabusDataInstructor) {
        val intent = Intent(this, UpdateSyllabusActivity::class.java)
        startActivity(intent)
    }
}