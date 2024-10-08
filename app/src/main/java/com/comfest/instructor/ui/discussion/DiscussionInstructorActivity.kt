package com.comfest.instructor.ui.discussion

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.domain.model.RequestCreateDiscussion
import com.comfest.instructor.ui.discussion.adapter.DiscussionInstructorAdapter
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityDiscussionInstructorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscussionInstructorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiscussionInstructorBinding
    private lateinit var discussionAdapter: DiscussionInstructorAdapter
    private lateinit var viewModel: DiscussionViewModel

    private var tokenUser: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDiscussionInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DiscussionViewModel::class.java]


        val course = intent.getParcelableExtra<Course>("course")

        viewModel.getToken().observe(this){token ->
            loadDiscussion(course?.CourseID!!, token )
            tokenUser = token
        }


        Glide.with(this)
            .load(course?.ImageURL)
            .centerCrop()
            .into(binding.ivDetailCourse)

        binding.tvTitle.text = course?.Title
        binding.tvDesc.text = course?.Description
        binding.ratingBar.rating = course?.Rating!!.toFloat()


        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnSendDiscussion.setOnClickListener {
            addMessageDiscussion(course.CourseID)
        }

        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        discussionAdapter = DiscussionInstructorAdapter()
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = discussionAdapter
        }
    }

    private fun addMessageDiscussion(id: Int) {
        binding.apply {
            val contentMessage = binding.edMessageDiscussion.text.toString()

            if (contentMessage.isEmpty()) {
                Toast.makeText(this@DiscussionInstructorActivity, "Please fill in all the field", Toast.LENGTH_SHORT).show()
            }

            val requestCreateDiscussion = RequestCreateDiscussion(
                courseId = id,
                messageDiscussion = contentMessage

            )

            viewModel.createMessageDiscussion(tokenUser, requestCreateDiscussion).observe(this@DiscussionInstructorActivity){
                when(it) {
                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {
                        Toast.makeText(this@DiscussionInstructorActivity, "Discussion message created successfully", Toast.LENGTH_SHORT).show()
                        loadDiscussion(id, tokenUser)
                    }

                    is Resource.Error -> {
                        Toast.makeText(this@DiscussionInstructorActivity, "Failed to create message: ${it.message}", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(
                            this@DiscussionInstructorActivity,
                            "Recheck your input discussion",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


    private fun loadDiscussion(courseId: Int, token: String) {
        viewModel.getDiscussion(courseId, token).observe(this){
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {

                    val discussion = it.data?.body()?.forumPosts

                    if (discussion.isNullOrEmpty()) {
                        binding.ivNoData.visibility = View.VISIBLE
                        binding.tvNoData.visibility = View.VISIBLE
                        binding.edMessageDiscussion.visibility = View.GONE
                        binding.btnSendDiscussion.visibility = View.GONE
                        binding.rvList.visibility = View.GONE
                    } else {
                        binding.ivNoData.visibility = View.GONE
                        binding.tvNoData.visibility = View.GONE
                        binding.rvList.visibility = View.VISIBLE

                        discussionAdapter.setDiscussion(discussion)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(this@DiscussionInstructorActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}