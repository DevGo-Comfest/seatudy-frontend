package com.comfest.seatudy.ui.home.viewall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityViewAllBinding
import com.comfest.seatudy.ui.home.HomeViewModel
import com.comfest.seatudy.ui.home.adapter.AdapterCourseSearch
import com.comfest.seatudy.ui.home.adapter.AdapterListCategories
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewAllActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewAllBinding
    private lateinit var adapterCategory: AdapterListCategories
    private lateinit var adapterSearch: AdapterSearch
    private lateinit var adapterCourseSearch: AdapterCourseSearch
    private lateinit var homeViewModel: HomeViewModel

    var category = ""
    var level = ""
    var rating = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        dataSearch()

        binding.btnSearch.setOnClickListener {
            if (category.isNotEmpty() && level.isNotEmpty() && rating.isNotEmpty()) {
                homeViewModel.getSearchCategoryLevelRating(category, level, rating)
                    .observe(this) { value ->
                        when (value) {
                            is Resource.Loading -> {

                            }

                            is Resource.Success -> {
                                val data = value.data?.body()?.courses
                                if (data != null) {
                                    adapterCourseSearch = AdapterCourseSearch(data)
                                    binding.rvCourse.layoutManager = LinearLayoutManager(
                                        this,
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                    binding.rvCourse.adapter = adapterCourseSearch
                                }
                            }

                            is Resource.Error -> {

                            }
                        }
                    }
            }
        }
    }

    private fun dataSearch() {
        homeViewModel.getCourse().observe(this) { data ->
            when (data) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    val data = data.data?.body()?.courses
                    if (data != null) {
                        adapterCategory = AdapterListCategories(data) {
                            category = it
                        }
                        binding.rvCategories.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                        binding.rvCategories.adapter = adapterCategory

                        val dataLevel = listOf("beginner", "intermediate", "advanced")
                        adapterSearch = AdapterSearch(dataLevel) {
                            level = it
                        }
                        binding.rvLevel.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                        binding.rvLevel.adapter = adapterSearch

                        val dataRating = listOf("0", "1", "2", "3", "4", "5")
                        adapterSearch = AdapterSearch(dataRating) {
                            rating = it
                        }
                        binding.rvRating.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                        binding.rvRating.adapter = adapterSearch

                    }
                }

                is Resource.Error -> {

                }
            }
        }
    }
}