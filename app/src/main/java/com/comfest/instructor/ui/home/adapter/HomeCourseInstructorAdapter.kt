package com.comfest.instructor.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comfest.instructor.data.dummy.CourseInstructor
import com.comfest.seatudy.databinding.ItemMycourseInstructorBinding

class HomeCourseInstructorAdapter(
    private val listener: OnItemClickListener
): RecyclerView.Adapter<HomeCourseInstructorAdapter.HomeCourseInstructorViewHolder>() {


    private var courses: List<CourseInstructor> = emptyList()

    fun setCourses(newCourses: List<CourseInstructor>) {
        courses = newCourses
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCourseInstructorViewHolder {
        val binding = ItemMycourseInstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeCourseInstructorViewHolder(binding)
    }

    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: HomeCourseInstructorViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    inner class HomeCourseInstructorViewHolder(private val binding: ItemMycourseInstructorBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(courses[position])
                }
            }
        }

        fun bind(course: CourseInstructor) {
            binding.tvNameCourse.text = course.name
            binding.ratingBar.rating = course.rating.toFloat()
            binding.tvTime.text = course.duration

            binding.ivCourse.setImageResource(course.image)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(course: CourseInstructor)
    }
}