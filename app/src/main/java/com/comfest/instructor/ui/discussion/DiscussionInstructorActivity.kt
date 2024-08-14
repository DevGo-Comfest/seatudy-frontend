package com.comfest.instructor.ui.discussion

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.ui.discussion.adapter.DiscussionInstructorAdapter
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityDiscussionInstructorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscussionInstructorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiscussionInstructorBinding
    private lateinit var discussionAdapter: DiscussionInstructorAdapter
    private lateinit var viewModel: DiscussionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDiscussionInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DiscussionViewModel::class.java]


        val course = intent.getParcelableExtra<Course>("course")

        viewModel.getToken().observe(this){token ->
            loadDiscussion(course?.CourseID!!, token )
        }


        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        setupRecyclerView()
//        loadDiscussion()
    }


    private fun setupRecyclerView() {
        discussionAdapter = DiscussionInstructorAdapter()
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = discussionAdapter
        }
    }


    private fun loadDiscussion(courseId: Int, token: String) {
        viewModel.getDiscussion(courseId, token).observe(this){
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    discussionAdapter.setDiscussion(it.data?.body()?.forumPosts!!)
                    val message = it.data?.body()?.forumPosts!!.map { item -> item.content }
                    Log.d("DiscussionInstructorActivity", "DATA CONTENT: $message")
                    Toast.makeText(this@DiscussionInstructorActivity, "Success get submision", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    Log.d("AssignmentInstructorActivity", "ERROR: ${it.message}")
                    Toast.makeText(this@DiscussionInstructorActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


//    private fun loadDiscussion() {
//        val sampleDiscussion = listOf(
//            DiscussionInstructor("Dewa Tri Wijaya", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
//            DiscussionInstructor("Joko Sukmo", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
//        )
//
//        discussionAdapter.setDiscussion(sampleDiscussion)
//    }
}