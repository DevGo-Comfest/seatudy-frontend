package com.comfest.instructor.ui.sylabus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.instructor.data.dummy.SyllabusDataInstructor
import com.comfest.instructor.data.source.remote.response.SyllabusDetail
import com.comfest.seatudy.databinding.ItemSyllabusInstructorBinding

class SyllabusInstructorAdapter(
    private val listener: OnItemClickListener
): RecyclerView.Adapter<SyllabusInstructorAdapter.SyllabusInstructorViewHolder>() {

    private var syllabus: List<SyllabusDetail> = emptyList()

    fun setSyllabus(newSyllabus: List<SyllabusDetail>) {
        syllabus = newSyllabus
        notifyDataSetChanged()

    }
    inner class SyllabusInstructorViewHolder(private val binding: ItemSyllabusInstructorBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(syllabusData: SyllabusDetail) {
            binding.apply {
                tvTitleSyllabus.text = syllabusData.title
                tvDescSyllabus.text = syllabusData.description
            }
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(syllabus[position])
                }
            }


            binding.btnUpdateSyllabus.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onUpdateClick(syllabus[position])
                }
            }

            binding.btnAddAssignment.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onAddAssignmentClick(syllabus[position])
                }
            }

            binding.btnDeleteSyllabus.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(syllabus[position])
                }
            }

            binding.btnAddSyllabusMaterial.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onAddSyllabusMaterialClick(syllabus[position])
                }
            }

            binding.btnSubmissionUser.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onSubmissionUser(syllabus[position])
                }
            }


        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SyllabusInstructorAdapter.SyllabusInstructorViewHolder {
        val binding = ItemSyllabusInstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SyllabusInstructorViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SyllabusInstructorAdapter.SyllabusInstructorViewHolder,
        position: Int
    ) {
        holder.bind(syllabus[position])
    }

    override fun getItemCount(): Int = syllabus.size

    interface OnItemClickListener {
        fun onItemClick(syllabus: SyllabusDetail)
        fun onUpdateClick(syllabus: SyllabusDetail)

        fun onAddAssignmentClick(syllabus: SyllabusDetail)
        fun onAddSyllabusMaterialClick(syllabus: SyllabusDetail)

        fun onDeleteClick(syllabus: SyllabusDetail)

        fun onSubmissionUser(syllabus: SyllabusDetail)
    }
}