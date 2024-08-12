package com.comfest.seatudy.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.comfest.seatudy.data.source.respon.ResponseCoursesDetail
import com.comfest.seatudy.databinding.ItemCardCourseHomeBinding
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailActivity

class AdapterCourseSearch(private val items: List<ResponseCoursesDetail>) :
    RecyclerView.Adapter<AdapterCourseSearch.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardCourseHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseCoursesDetail) {
            binding.tvNameCourse.text = item.title
            binding.tvRating.rating = item.rating.toFloat()
            binding.tvLevel.text = item.difficultyLevel
            Glide.with(itemView)
                .load(item.imageURL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.imgCourse)

            binding.cardCourse.setOnClickListener {
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
            ItemCardCourseHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterServiceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}