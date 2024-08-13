package com.comfest.seatudy.ui.home.viewall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.comfest.seatudy.R
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityViewAllBinding
import com.comfest.seatudy.ui.home.HomeViewModel
import com.comfest.seatudy.ui.home.adapter.AdapterListCategories
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewAllActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewAllBinding
    private lateinit var adapterCategory: AdapterListCategories
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val category = "Android"
        val level = "advanced"
        val rating = "0"

        homeViewModel.getSearchCategoryLevelRating(category, level, rating).observe(this){ value ->
            when(value){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val data = value.data?.body()
                    if (data != null){

                    }

                }
                is Resource.Error -> {

                }
            }
        }
    }
}