package com.comfest.instructor.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.dummy.CourseInstructor
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.ui.course.UpdateCourseActivity
import com.comfest.instructor.ui.home.adapter.HomeCourseInstructorAdapter
import com.comfest.seatudy.R
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.FragmentHomeInstructorBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeInstructorFragment : Fragment(), HomeCourseInstructorAdapter.OnItemClickListener {

    private var _binding: FragmentHomeInstructorBinding? = null
    private val binding get() = _binding!!
    private lateinit var courseAdapter: HomeCourseInstructorAdapter
    private lateinit var homeInstructorViewModel: HomeInstructorViewModel

    private var token: String ? = null

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

        homeInstructorViewModel = ViewModelProvider(this)[HomeInstructorViewModel::class.java]

        setUpRecyclerView()
//        loadCourses()
    }


    private fun setUpRecyclerView() {
//        courseAdapter = HomeCourseInstructorAdapter(this)
//        binding.rvList.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = courseAdapter
//        }

        homeInstructorViewModel.getToken().observe(viewLifecycleOwner) { tokenUser ->
//            token = tokenUser
            homeInstructorViewModel.getCourse(tokenUser).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        Toast.makeText(requireContext(), "Loading get course", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("HomeInstructorFragment", token ?: "")
                    }

                    is Resource.Success -> {
                        Toast.makeText(requireContext(), "Success get course", Toast.LENGTH_SHORT)
                            .show()
                        val data = it.data?.body()
                        if (data != null) {


                            courseAdapter = HomeCourseInstructorAdapter(this)
                            binding.rvList.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            binding.rvList.adapter = courseAdapter

                            courseAdapter.setCourses(data.course)
                        }
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Failed get course", Toast.LENGTH_SHORT).show()
                        Log.d("HomeInstructorFragment", token ?: "")
                    }
                }
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(course: Course) {
        val intent  = Intent(context, DetailCourseActivity::class.java)
        intent.putExtra("course", course)
        context?.startActivity(intent)
    }

    override fun onUpdateClick(course: Course) {
        val intent  = Intent(context, UpdateCourseActivity::class.java)
        intent.putExtra("course", course)
        context?.startActivity(intent)
    }
}