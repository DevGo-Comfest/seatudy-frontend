package com.comfest.instructor.ui.sylabus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.instructor.data.dummy.SyllabusDataInstructor
import com.comfest.seatudy.databinding.ItemSyllabusInstructorBinding

class SyllabusInstructorAdapter: RecyclerView.Adapter<SyllabusInstructorAdapter.SyllabusInstructorViewHolder>() {

    private var syllabus: List<SyllabusDataInstructor> = listOf()

    fun setSyllabus(newSyllabus: List<SyllabusDataInstructor>) {
        syllabus = newSyllabus
        notifyDataSetChanged()

    }
    inner class SyllabusInstructorViewHolder(private val binding: ItemSyllabusInstructorBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(syllabusData: SyllabusDataInstructor) {
            binding.apply {
                tvTitleSyllabus.text = syllabusData.title
                tvDescSyllabus.text = syllabusData.description
                tvAssignment.text = syllabusData.titleAssignment
                tvDueDate.text = syllabusData.dueDateAssignment.toString()
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
}