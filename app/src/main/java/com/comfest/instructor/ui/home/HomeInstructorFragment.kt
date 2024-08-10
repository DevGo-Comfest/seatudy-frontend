package com.comfest.instructor.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.dummy.CourseInstructor
import com.comfest.instructor.ui.course.UpdateCourseActivity
import com.comfest.instructor.ui.home.adapter.HomeCourseInstructorAdapter
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.FragmentHomeInstructorBinding


class HomeInstructorFragment : Fragment(), HomeCourseInstructorAdapter.OnItemClickListener {

    private var _binding: FragmentHomeInstructorBinding? = null
    private val binding get() = _binding!!
    private lateinit var courseAdapter: HomeCourseInstructorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeInstructorBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        loadCourses()
    }


    private fun setUpRecyclerView() {
        courseAdapter = HomeCourseInstructorAdapter(this)
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = courseAdapter
        }
    }


    private fun loadCourses() {
        val sampleCourses = listOf(
            CourseInstructor(1,  R.drawable.test_dummy ,"Software Engineer Mobile Development Android Engineer", 4, "12H"),
            CourseInstructor(2,  R.drawable.test_dummy,"iOS Development", 4, "10H"),
            CourseInstructor(3,  R.drawable.test_dummy,"Web Development", 5, "15H")
        )
        courseAdapter.setCourses(sampleCourses)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(course: CourseInstructor) {
        val intent = Intent(context, DetailCourseActivity::class.java).apply {
            putExtra("EXTRA_COURSE_ID", course.id)
            putExtra("EXTRA_COURSE_NAME", course.name)
            putExtra("EXTRA_COURSE_RATING", course.rating)
            putExtra("EXTRA_COURSE_DURATION", course.duration)
            putExtra("EXTRA_COURSE_IMAGE", course.image)
        }
        startActivity(intent)
    }

    override fun onUpdateClick(course: CourseInstructor) {
        val intent = Intent(context, UpdateCourseActivity::class.java)
        startActivity(intent)
    }

}