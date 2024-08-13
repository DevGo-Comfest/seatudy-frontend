package com.comfest.seatudy.ui.dashboard.detailcourse.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.FragmentForumsBinding
import com.comfest.seatudy.ui.dashboard.AdapterCourseDashboard
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailViewModel
import com.comfest.seatudy.ui.dashboard.detailcourse.adapter.AdapterForums
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForumsFragment(val courseID: String) : Fragment() {

    private var _binding: FragmentForumsBinding? = null
    private val binding get() = _binding!!
    private lateinit var courseDetailViewModel: CourseDetailViewModel
    private lateinit var adapterForums: AdapterForums
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForumsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseDetailViewModel = ViewModelProvider(this)[CourseDetailViewModel::class.java]

        courseDetailViewModel.getToken().observe(viewLifecycleOwner) { token ->
            courseDetailViewModel.getForumID(courseID, "Bearer $token").observe(viewLifecycleOwner) { data ->
                when (data) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        val dataForum = data.data?.body()?.forumPosts
                        if (dataForum != null) {
                            adapterForums = AdapterForums(dataForum)
                            binding.rvForum.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            binding.rvForum.adapter = adapterForums
                        }
                    }

                    is Resource.Error -> {

                    }
                }
            }
        }
    }

}