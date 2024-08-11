package com.comfest.seatudy.ui.dashboard

import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.comfest.seatudy.databinding.ItemCardCourseProgramBinding
import com.comfest.seatudy.domain.model.DataCourseList
import com.comfest.seatudy.ui.dashboard.detail.CourseDetailActivity

class AdapterCourseDashboard(private val items: List<DataCourseList>) :
    RecyclerView.Adapter<AdapterCourseDashboard.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardCourseProgramBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataCourseList) {
            binding.tvNameCourse.text = item.nameCourse
            binding.tvProgress.text = "Progress"
            binding.tvProgressSyllabus.text = "2/4 Syllabus"
            binding.tvSumProgress.progress = item.progress.toInt()
//            Glide.with(itemView)
//                .load(item.)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .centerCrop()
//                .into(binding.imgCourse)

            binding.card.setOnClickListener {
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
            ItemCardCourseProgramBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterServiceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}