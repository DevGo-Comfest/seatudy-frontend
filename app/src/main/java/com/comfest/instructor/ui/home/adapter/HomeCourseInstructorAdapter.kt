package com.comfest.instructor.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.comfest.instructor.data.dummy.CourseInstructor
import com.comfest.instructor.data.source.remote.response.Course
import com.comfest.instructor.data.source.remote.response.CourseResponse
import com.comfest.seatudy.databinding.ItemMycourseInstructorBinding

class HomeCourseInstructorAdapter(
    private val listener: OnItemClickListener
): RecyclerView.Adapter<HomeCourseInstructorAdapter.HomeCourseInstructorViewHolder>() {


    private var courses: List<Course> = emptyList()

    fun setCourses(newCourses: List<Course>) {
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
                if (position != RecyclerView.NO_POSITION && position < courses.size) {
                    listener.onItemClick(courses[position])
                }
            }

            binding.btnUpdate.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION && position < courses.size) {
                    listener.onUpdateClick(courses[position])
                }
            }
        }


        fun bind(course: Course) {
            binding.tvNameCourse.text = course.Title
            binding.ratingBar.rating = course.Rating.toFloat()
            binding.tvTime.text = "test"

            Glide.with(itemView.context)
                .load(course.ImageURL)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.ivCourse)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(course: Course)
        fun onUpdateClick(course: Course)
    }
}