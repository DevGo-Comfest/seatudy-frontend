package com.comfest.instructor.ui.assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.instructor.data.dummy.InstructorData
import com.comfest.seatudy.databinding.ItemAssignInstructorBinding

class AddInstructorAdapter: RecyclerView.Adapter<AddInstructorAdapter.AddInstructorViewHolder>() {


    private var instructorData: List<InstructorData> = emptyList()
    private val selectedItems = mutableSetOf<Int>()

    fun setInstructor(newInstructor: List<InstructorData>) {
        instructorData = newInstructor
        notifyDataSetChanged()
    }

    inner class AddInstructorViewHolder(private val binding: ItemAssignInstructorBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(instructor: InstructorData) {
            binding.checkBox.isChecked = selectedItems.contains(adapterPosition)
            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedItems.add(adapterPosition)
                } else {
                    selectedItems.remove(adapterPosition)
                }
            }

            binding.tvNameInstructor.text = instructor.name

            itemView.setOnClickListener {
                toggleSelection()
            }
        }

        private fun toggleSelection() {
            val position = adapterPosition
            if (selectedItems.contains(position)) {
                selectedItems.remove(position)
            } else {
                selectedItems.add(position)
            }
            notifyItemChanged(position) // Update the visual state
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddInstructorViewHolder {
        val binding = ItemAssignInstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddInstructorViewHolder(binding)
    }

    override fun getItemCount(): Int = instructorData.size

    override fun onBindViewHolder(holder: AddInstructorViewHolder, position: Int) {
        holder.bind(instructorData[position])
    }


    fun getSelectedItem(): List<InstructorData> {
        return selectedItems.map { instructorData[it] }
    }
}