package com.comfest.seatudy.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.FragmentHomeBinding
import com.comfest.seatudy.ui.home.adapter.AdapterCourseCategories
import com.comfest.seatudy.ui.home.adapter.AdapterCourseList
import com.comfest.seatudy.ui.home.adapter.AdapterCourseSearch
import com.comfest.seatudy.ui.home.adapter.AdapterListCategories
import com.comfest.seatudy.ui.home.viewall.ViewAllActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterCourseList: AdapterCourseList
    private lateinit var adapterCourseCategories: AdapterCourseCategories
    private lateinit var adapterListCategories: AdapterListCategories
    private lateinit var adapterCourseSearch: AdapterCourseSearch
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.viewAll1.setOnClickListener {
            viewAll()
        }

        binding.viewAll2.setOnClickListener {
            viewAll()
        }

        recyclerviewCourseList()
        recyclerviewCategories()
        recyclerviewCourseCategories("Android")
        getName()
        search()
    }

    private fun viewAll() {
        startActivity(Intent(requireContext(), ViewAllActivity::class.java))
    }

    private fun search() {
        binding.searchCourse.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.rvCourseSearch.visibility = View.VISIBLE
                homeViewModel.getSearchCourseName(query).observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            val data = it.data?.body()
                            if (data != null) {
                                adapterCourseSearch = AdapterCourseSearch(data.courses)
                                binding.rvCourseSearch.layoutManager = LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                                binding.rvCourseSearch.adapter = adapterCourseList
                            }
                        }

                        is Resource.Error -> {
                            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                binding.rvCourseSearch.visibility = View.GONE
                return true
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun getName() {
        homeViewModel.getName().observe(viewLifecycleOwner) {
            binding.tvName.text = "Hi, $it!"
        }
    }

    private fun recyclerviewCourseList() {
        homeViewModel.getCourse().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    val data = it.data?.body()
                    if (data != null) {
                        adapterCourseList = AdapterCourseList(data.courses)
                        binding.rvCourse.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        binding.rvCourse.adapter = adapterCourseList
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error Occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun recyclerviewCategories() {
        homeViewModel.getCourse().observe(viewLifecycleOwner) { value ->
            when(value){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val data = value.data?.body()?.courses
                    if (data != null) {
                        adapterListCategories = AdapterListCategories(data) { category ->
                            recyclerviewCourseCategories(category)
                        }
                        binding.rvCourseCategories.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        binding.rvCourseCategories.adapter = adapterListCategories
                    }
                }
                is Resource.Error -> {

                }
            }
        }
    }

    private fun recyclerviewCourseCategories(category: String) {
        homeViewModel.getCourseCategory(category).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    val data = it.data?.body()
                    if (data != null) {
                        adapterCourseCategories = AdapterCourseCategories(data.courses)
                        binding.rvCourseCategoriesList.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        binding.rvCourseCategoriesList.adapter = adapterCourseCategories
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error Occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}