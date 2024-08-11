package com.comfest.instructor.ui.sylabus

import android.content.Intent
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

class SyllabusMaterialInstructorActivity : AppCompatActivity(), SyllabusMaterialInstructorAdapter.OnItemClickListener {
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
        syllabusAdapter = SyllabusMaterialInstructorAdapter(this)

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

    override fun onUpdateClick(syllabusMaterial: SyllabusMaterialInstructor) {
        val intent = Intent(this, UpdateSyllabusMaterialActivity::class.java)
        startActivity(intent)
    }
}