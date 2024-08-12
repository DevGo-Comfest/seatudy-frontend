package com.comfest.seatudy.ui.dashboard.detailcourse.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.seatudy.databinding.ItemCardSyllabusBinding
import com.comfest.seatudy.domain.model.DataCourseList
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailActivity

class AdapterCourseProgram(private val items: List<DataCourseList>) :
    RecyclerView.Adapter<AdapterCourseProgram.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardSyllabusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataCourseList) {
            binding.tvNameCourse.text = item.nameCourse

            binding.imgCourse.visibility

            binding.cardCourse.setOnClickListener {
                val intent =
                    Intent(itemView.context, CourseDetailActivity::class.java).apply {
                        putExtra("TITLE", item.nameCourse)
                    }
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterServiceViewHolder {
        val binding =
            ItemCardSyllabusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterServiceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}