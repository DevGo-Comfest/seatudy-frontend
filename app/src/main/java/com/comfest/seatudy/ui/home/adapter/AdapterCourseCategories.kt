package com.comfest.seatudy.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.comfest.seatudy.databinding.ItemCardCourseCategoriesBinding
import com.comfest.seatudy.domain.model.DataCourseList
import com.comfest.seatudy.ui.dashboard.detail.CourseDetailActivity

class AdapterCourseCategories(private val items: List<DataCourseList>) :
    RecyclerView.Adapter<AdapterCourseCategories.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardCourseCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataCourseList) {
            binding.tvNameCourse.text = item.nameCourse
            Glide.with(itemView)
                .load(item.imageURL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.imgCourse)
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