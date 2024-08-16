package com.comfest.seatudy.ui.dashboard.detailcourse.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.FragmentInstructorsBinding
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstructorsFragment(val courseID: String) : Fragment() {

    private var _binding: FragmentInstructorsBinding? = null
    private val binding get() = _binding!!
    private lateinit var courseDetailViewModel: CourseDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseDetailViewModel = ViewModelProvider(this)[CourseDetailViewModel::class.java]

        courseDetailViewModel.getCoursesWithID(courseID).observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val data = it.data?.body()?.courses
                    if (data != null) {
                        binding.tvName.text = data.primaryAuthorName
                        }
                }
                is Resource.Error -> {

                }
            }
        }
    }

}