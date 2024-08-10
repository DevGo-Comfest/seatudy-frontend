package com.comfest.instructor.ui.discussion

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.dummy.DiscussionInstructor
import com.comfest.instructor.ui.discussion.adapter.DiscussionInstructorAdapter
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.ActivityDiscussionInstructorBinding

class DiscussionInstructorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiscussionInstructorBinding
    private lateinit var discussionAdapter: DiscussionInstructorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDiscussionInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        setupRecyclerView()
        loadDiscussion()
    }


    private fun setupRecyclerView() {
        discussionAdapter = DiscussionInstructorAdapter()
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = discussionAdapter
        }
    }


    private fun loadDiscussion() {
        val sampleDiscussion = listOf(
            DiscussionInstructor("Dewa Tri Wijaya", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
            DiscussionInstructor("Joko Sukmo", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
        )

        discussionAdapter.setDiscussion(sampleDiscussion)
    }
}