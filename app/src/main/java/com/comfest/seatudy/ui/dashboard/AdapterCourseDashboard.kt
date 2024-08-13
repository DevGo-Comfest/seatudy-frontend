package com.comfest.seatudy.ui.dashboard

import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.comfest.seatudy.data.source.respon.ResponseEnrolledCourse
import com.comfest.seatudy.databinding.ItemCardCourseProgramBinding
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailActivity

class AdapterCourseDashboard(
    private val items: List<ResponseEnrolledCourse>
) :
    RecyclerView.Adapter<AdapterCourseDashboard.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardCourseProgramBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseEnrolledCourse) {
            binding.tvNameCourse.text = item.title
            binding.tvProgress.text = "Progress"
            binding.tvProgressSyllabus.text = "1 Syllabus"

            Glide.with(itemView)
                .load(item.imageURL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.imgCourse)

            binding.card.setOnClickListener {
                val intent =
                    Intent(itemView.context, CourseDetailActivity::class.java).apply {
                        putExtra("TITLE", item.courseID.toString())
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