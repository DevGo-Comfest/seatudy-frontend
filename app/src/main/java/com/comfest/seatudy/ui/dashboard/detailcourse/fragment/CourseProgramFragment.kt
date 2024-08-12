package com.comfest.seatudy.ui.dashboard.detailcourse.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.FragmentCourseProgramBinding
import com.comfest.seatudy.ui.home.HomeViewModel
import com.comfest.seatudy.ui.home.adapter.AdapterCourseList


class CourseProgramFragment : Fragment() {

    private var _binding: FragmentCourseProgramBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterCourseList: AdapterCourseList
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_course_program, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // val courseID = frintent.getStringExtra("TITLE").toString()

    }

}