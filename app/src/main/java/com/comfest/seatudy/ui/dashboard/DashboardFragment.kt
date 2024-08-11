package com.comfest.seatudy.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.seatudy.dataDummy.DataDummy
import com.comfest.seatudy.databinding.FragmentDashboardBinding
import com.comfest.seatudy.ui.home.adapter.AdapterCourseList

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterCourseDashboard: AdapterCourseDashboard

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerviewCourseList()
    }

    private fun recyclerviewCourseList(){
        adapterCourseDashboard = AdapterCourseDashboard(DataDummy.listDataCourse)
        binding.rvMyCourse.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvMyCourse.adapter = adapterCourseDashboard
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}