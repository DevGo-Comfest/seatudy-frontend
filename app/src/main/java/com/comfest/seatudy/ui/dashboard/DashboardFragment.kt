package com.comfest.seatudy.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.seatudy.data.Resource
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
        dashboardViewModel.getToken().observe(viewLifecycleOwner) {
            dashboardViewModel.getEnrolledCourse("Bearer $it").observe(viewLifecycleOwner) { value ->
                when(value){
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        val data = value.data?.body()
                        if (data != null){
                            adapterCourseDashboard = AdapterCourseDashboard(data.courses)
                            binding.rvMyCourse.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            binding.rvMyCourse.adapter = adapterCourseDashboard
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