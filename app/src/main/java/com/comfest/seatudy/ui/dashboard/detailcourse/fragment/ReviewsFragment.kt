package com.comfest.seatudy.ui.dashboard.detailcourse.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.comfest.seatudy.databinding.FragmentReviewsBinding
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewsFragment(val courseID: String) : Fragment() {

    private var _binding: FragmentReviewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var courseDetailViewModel: CourseDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseDetailViewModel = ViewModelProvider(this)[CourseDetailViewModel::class.java]

        binding.tvName.text = ""
        binding.tvReview.text = ""
        binding.tvRating.rating = "2".toFloat()
    }

}