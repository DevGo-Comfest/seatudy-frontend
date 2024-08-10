package com.comfest.instructor.ui.assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.instructor.data.dummy.AssignmentInstructor
import com.comfest.seatudy.databinding.ItemAssignmentInstructorBinding

class AssignmentInstructorAdapter: RecyclerView.Adapter<AssignmentInstructorAdapter.AssignmentInstructorViewHolder>() {

    private var assignment: List<AssignmentInstructor> = emptyList()

    fun setAssignment(newAssignment: List<AssignmentInstructor>) {
        assignment = newAssignment
        notifyDataSetChanged()
    }

    inner class AssignmentInstructorViewHolder(private val binding: ItemAssignmentInstructorBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(assignmentData: AssignmentInstructor) {
            binding.apply {
                tvNameStudent.text = assignmentData.nameStudent
                tvAttachmentStudent.text = assignmentData.linkAttachment
                submitedAt.text = assignmentData.dateSubmit
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AssignmentInstructorViewHolder {
        val binding = ItemAssignmentInstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  AssignmentInstructorViewHolder(binding)
    }

    override fun getItemCount(): Int = assignment.size

    override fun onBindViewHolder(holder: AssignmentInstructorViewHolder, position: Int) {
        holder.bind(assignment[position])
    }
}