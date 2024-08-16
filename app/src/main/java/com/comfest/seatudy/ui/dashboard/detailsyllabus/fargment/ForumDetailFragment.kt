package com.comfest.seatudy.ui.dashboard.detailsyllabus.fargment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.DialogAddForumBinding
import com.comfest.seatudy.databinding.FragmentForumDetailBinding
import com.comfest.seatudy.domain.model.DataForum
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailViewModel
import com.comfest.seatudy.ui.dashboard.detailcourse.adapter.AdapterForums
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForumDetailFragment(val courseID: String) : Fragment() {

    private var _binding: FragmentForumDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var courseDetailViewModel: CourseDetailViewModel
    private lateinit var adapterForums: AdapterForums
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForumDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseDetailViewModel = ViewModelProvider(this)[CourseDetailViewModel::class.java]

        courseDetailViewModel.getToken().observe(viewLifecycleOwner) { token ->
            courseDetailViewModel.getForumID(courseID, "Bearer $token")
                .observe(viewLifecycleOwner) { data ->
                    when (data) {
                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            val dataForum = data.data?.body()?.forumPosts
                            if (dataForum != null) {
                                adapterForums = AdapterForums(dataForum)
                                binding.rvForum.layoutManager = LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                                binding.rvForum.adapter = adapterForums
                            }
                        }

                        is Resource.Error -> {

                        }
                    }
                }
        }
        binding.btnAddForum.setOnClickListener {
            forumDialog()
        }
    }

    private fun forumDialog() {
        val dialogBinding = DialogAddForumBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .create()

        dialogBinding.apply {
            btnSave.setOnClickListener {
                val description = tvDescription.text.toString()

                if (description.isNotEmpty()) {
                    courseDetailViewModel.getToken().observe(viewLifecycleOwner) { token ->
                        courseDetailViewModel.sendPostForum(
                            DataForum(
                                courseID.toInt(),
                                description
                            ), "Bearer $token"
                        ).observe(viewLifecycleOwner) {
                            when (it) {
                                is Resource.Loading -> {

                                }

                                is Resource.Success -> {
                                    builder.dismiss()
                                }

                                is Resource.Error -> {

                                }
                            }

                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        dialogBinding.btnClose.setOnClickListener {
            builder.dismiss()
        }
        builder.show()
    }
}