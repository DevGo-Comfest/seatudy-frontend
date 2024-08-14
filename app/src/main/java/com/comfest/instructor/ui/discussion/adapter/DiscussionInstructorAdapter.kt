package com.comfest.instructor.ui.discussion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.instructor.data.dummy.DiscussionInstructor
import com.comfest.instructor.data.source.remote.response.Discussion
import com.comfest.seatudy.databinding.ItemDiscussionInstructorBinding

class DiscussionInstructorAdapter: RecyclerView.Adapter<DiscussionInstructorAdapter.DiscussionInstructorViewHolder>() {

    private var discussion: List<Discussion> = emptyList()

    fun setDiscussion(newDiscussion: List<Discussion>) {
        discussion = newDiscussion
        notifyDataSetChanged()
    }
    inner class DiscussionInstructorViewHolder(private val binding: ItemDiscussionInstructorBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(discussionData: Discussion) {
            binding.apply {
                tvNameUser.text = discussionData.userName
                tvDiscussion.text = discussionData.content
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiscussionInstructorAdapter.DiscussionInstructorViewHolder {
        val binding = ItemDiscussionInstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscussionInstructorViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DiscussionInstructorAdapter.DiscussionInstructorViewHolder,
        position: Int
    ) {
        holder.bind(discussion[position])
    }

    override fun getItemCount(): Int = discussion.size
}