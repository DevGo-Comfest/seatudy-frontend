package com.comfest.instructor.ui.sylabus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.dummy.SyllabusMaterialInstructor
import com.comfest.instructor.ui.sylabus.adapter.SyllabusMaterialInstructorAdapter
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.ActivitySyllabusMaterialInstructorBinding

class SyllabusMaterialInstructorActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySyllabusMaterialInstructorBinding
    private lateinit var syllabusAdapter: SyllabusMaterialInstructorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySyllabusMaterialInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupRecyclerView()
        loadSyllabusMaterial()

    }


    fun setupRecyclerView() {
        syllabusAdapter = SyllabusMaterialInstructorAdapter()

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(this@SyllabusMaterialInstructorActivity)
            adapter = syllabusAdapter
        }
    }

    fun loadSyllabusMaterial() {
        val dummyMaterial = listOf(
            SyllabusMaterialInstructor(
                title = "Software Enginering",
                desc = "Learn the principles of software engineering.",
                linkMaterial = "https://youtu.be/0BIaPnxfYf8?si=zd-PYWLuJbGLxFeu",
            ),

            SyllabusMaterialInstructor(
                title = "Design System",
                desc = "Learn the principles of software engineering.",
                linkMaterial = "https://youtu.be/XhF9LnpNtqg?si=jEGgylW3U4vre37s",
            ),
        )

        syllabusAdapter.setSyllabusMaterial(dummyMaterial)
    }
}