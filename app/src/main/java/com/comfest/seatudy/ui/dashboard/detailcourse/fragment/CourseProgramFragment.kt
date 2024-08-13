package com.comfest.seatudy.ui.dashboard.detailcourse.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.FragmentCourseProgramBinding
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailViewModel
import com.comfest.seatudy.ui.dashboard.detailcourse.adapter.AdapterCourseProgram
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseProgramFragment(val courseID: String) : Fragment() {

    private var _binding: FragmentCourseProgramBinding? = null
    private val binding get() = _binding!!
    private lateinit var courseDetailViewModel: CourseDetailViewModel
    private lateinit var adapterCourseProgram: AdapterCourseProgram

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseDetailViewModel = ViewModelProvider(this)[CourseDetailViewModel::class.java]

        courseDetailViewModel.getToken().observe(viewLifecycleOwner) {
            courseDetailViewModel.getCoursesWithID(courseID).observe(viewLifecycleOwner) { dataID ->
                when (dataID) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        val data = dataID.data?.body()?.courses?.syllabuses
                        if (data != null) {
                            adapterCourseProgram = AdapterCourseProgram(data, false, data.size)
                            binding.rvSyllabus.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            binding.rvSyllabus.adapter = adapterCourseProgram
                        }

                        courseDetailViewModel.getEnrolledCourse("Bearer $it").observe(viewLifecycleOwner) { value ->
                                when (value) {
                                    is Resource.Loading -> {

                                    }

                                    is Resource.Success -> {
                                        val isCourseEnrolled = value.data?.body()?.courses?.any { it.courseID == courseID.toInt() } ?: false
                                        if (isCourseEnrolled) {
                                            val data = dataID.data?.body()?.courses?.syllabuses
                                            if (data != null) {
                                                Log.d("CEK SYLLABUS", "${data.size}")
                                                adapterCourseProgram = AdapterCourseProgram(data, true, data.size)
                                                binding.rvSyllabus.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                                                binding.rvSyllabus.adapter = adapterCourseProgram
                                            }
                                        }
                                    }

                                    is Resource.Error -> {

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

}