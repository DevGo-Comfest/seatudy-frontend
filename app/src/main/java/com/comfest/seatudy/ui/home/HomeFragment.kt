package com.comfest.seatudy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comfest.seatudy.dataDummy.DataDummy
import com.comfest.seatudy.databinding.FragmentHomeBinding
import com.comfest.seatudy.ui.home.adapter.AdapterCourseCategories
import com.comfest.seatudy.ui.home.adapter.AdapterCourseList
import com.comfest.seatudy.ui.home.adapter.AdapterListCategories

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterCourseList: AdapterCourseList
    private lateinit var adapterCourseCategories: AdapterCourseCategories
    private lateinit var adapterListCategories: AdapterListCategories

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerviewCourseList()
        recyclerviewCourseCategories()
        recyclerviewCategories()
    }

    private fun recyclerviewCourseList(){
        adapterCourseList = AdapterCourseList(DataDummy.listDataCourse)
        binding.rvCourse.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCourse.adapter = adapterCourseList
    }

    private fun recyclerviewCategories(){
        adapterListCategories = AdapterListCategories(DataDummy.listDataCourse)
        binding.rvCourseCategories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCourseCategories.adapter = adapterListCategories
    }



    private fun recyclerviewCourseCategories(){
        adapterCourseCategories = AdapterCourseCategories(DataDummy.listDataCourse)
        binding.rvCourseCategoriesList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvCourseCategoriesList.adapter = adapterCourseCategories
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}