package com.comfest.instructor.ui.sylabus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.instructor.data.source.remote.response.AssignmentSyllabus
import com.comfest.seatudy.databinding.ItemAssignmentSyllabusInstructorBinding

class AssignmentSyllabusAdapter: RecyclerView.Adapter<AssignmentSyllabusAdapter.AssignmentSyllabusViewHolder>() {

    private var assignment: List<AssignmentSyllabus> = emptyList()

    fun setAssignment(newAssignment: List<AssignmentSyllabus>) {
        assignment = newAssignment
        notifyDataSetChanged()
    }

    inner class AssignmentSyllabusViewHolder(private val binding: ItemAssignmentSyllabusInstructorBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(assignment: AssignmentSyllabus) {
            binding.apply {
                tvTitleAssignment.text = assignment.title
                tvDescAssignment.text = assignment.description
                tvDueDate.text = assignment.maximumTime.toString()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AssignmentSyllabusAdapter.AssignmentSyllabusViewHolder {
        val binding = ItemAssignmentSyllabusInstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssignmentSyllabusViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AssignmentSyllabusAdapter.AssignmentSyllabusViewHolder,
        position: Int
    ) {
        holder.bind(assignment[position])
    }

    override fun getItemCount(): Int  = assignment.size

}