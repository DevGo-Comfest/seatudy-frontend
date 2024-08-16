package com.comfest.seatudy.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.data.source.respon.ResponseSyllabuses
import com.comfest.seatudy.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterCourseDashboard: AdapterCourseDashboard
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        recyclerviewCourseList()
    }

    private fun recyclerviewCourseList() {
        dashboardViewModel.getToken().observe(viewLifecycleOwner) { token ->
            dashboardViewModel.getEnrolledCourse("Bearer $token").observe(viewLifecycleOwner) { dataEnroll ->
                when (dataEnroll) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        Log.d("CEK ENROLL", "$dataEnroll")
                        val courseID = dataEnroll.data?.body()?.courses
                        courseID?.forEach { idCourse ->
                            dashboardViewModel.getCoursesWithID(idCourse.courseID.toString())
                                .observe(viewLifecycleOwner) { dataCourseWithID ->
                                    when (dataCourseWithID) {
                                        is Resource.Loading -> {

                                        }

                                        is Resource.Success -> {
                                            Log.d("CEK WITHID", "$dataCourseWithID")
                                            val dataWithID = dataCourseWithID.data?.body()?.courses
                                            if (dataWithID != null) {
                                                var progressData = 0
                                                dashboardViewModel.getProgress(
                                                    idCourse.courseID.toString(),
                                                    "Bearer $token"
                                                ).observe(viewLifecycleOwner) { progress ->
                                                    val dataProgress = progress.data?.body()
                                                    when (progress) {
                                                        is Resource.Loading -> {
                                                        }

                                                        is Resource.Success -> {
                                                            if (dataProgress != null) {
                                                                progressData = dataProgress.progress
                                                            }
                                                        }

                                                        is Resource.Error -> {

                                                        }
                                                    }

                                                }
                                                val isCourseEnrolled =
                                                    dataEnroll.data.body()?.courses?.any { it.courseID == dataWithID.courseID }
                                                        ?: false

                                                if (isCourseEnrolled) {
                                                    adapterCourseDashboard = AdapterCourseDashboard(
                                                        courseID,
                                                        progressData,
                                                        dataWithID
                                                    )
                                                    binding.rvMyCourse.layoutManager =
                                                        LinearLayoutManager(
                                                            context,
                                                            LinearLayoutManager.VERTICAL,
                                                            false
                                                        )
                                                    binding.rvMyCourse.adapter =
                                                        adapterCourseDashboard
                                                }
                                            }
                                        }

                                        is Resource.Error -> {

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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}