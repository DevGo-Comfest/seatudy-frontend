package com.comfest.instructor.ui.assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.instructor.data.dummy.AssignmentInstructor
import com.comfest.instructor.data.source.remote.response.Submission
import com.comfest.seatudy.databinding.ItemAssignmentInstructorBinding
import com.comfest.seatudy.utils.DateFormatter

class AssignmentInstructorAdapter(
    private val listener: OnItemClickListener
): RecyclerView.Adapter<AssignmentInstructorAdapter.AssignmentInstructorViewHolder>() {

    private var assignment: List<Submission> = emptyList()

    fun setAssignment(newAssignment: List<Submission>) {
        assignment = newAssignment
        notifyDataSetChanged()
    }

    inner class AssignmentInstructorViewHolder(private val binding: ItemAssignmentInstructorBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(assignmentData: Submission) {
            binding.apply {
                tvNameStudent.text = "Student"
                tvAttachmentStudent.text = assignmentData.contentURL
                val formattedDate = DateFormatter.formatDate(assignmentData.createdAt)
                submitedAt.text = formattedDate
                edGradeSubmission.setText(assignmentData.grade.toString())
            }
        }

        init {
            binding.btnGiveAGrade.setOnClickListener {
                val position = adapterPosition
                val grade = binding.edGradeSubmission.text.toString()
                if (position != RecyclerView.NO_POSITION) {
                    listener.onAddGradeClick(assignment[position], grade.toInt())
                }
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

    interface OnItemClickListener{
        fun onAddGradeClick(submission: Submission, grade: Int)
    }
}