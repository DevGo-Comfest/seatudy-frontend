package com.comfest.seatudy.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.comfest.seatudy.data.source.respon.ResponseCoursesDetail
import com.comfest.seatudy.databinding.ItemCardCourseCategoriesBinding
import com.comfest.seatudy.ui.dashboard.detailcourse.CourseDetailActivity

class AdapterCourseCategories(private val items: List<ResponseCoursesDetail>) :
    RecyclerView.Adapter<AdapterCourseCategories.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardCourseCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseCoursesDetail) {
            binding.tvNameCourse.text = item.title
            Glide.with(itemView)
                .load(item.imageURL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.imgCourse)
            binding.cardCourse.setOnClickListener {
                val intent =
                    Intent(itemView.context, CourseDetailActivity::class.java).apply {
                        putExtra("TITLE", item.courseID)
                    }
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterServiceViewHolder {
        val binding =
            ItemCardCourseCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterServiceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}