package com.comfest.seatudy.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.seatudy.databinding.ItemCardCategoriesBinding
import com.comfest.seatudy.domain.model.DataCourseList
import com.comfest.seatudy.ui.dashboard.detail.CourseDetailActivity

class AdapterListCategories(private val items: List<DataCourseList>) :
    RecyclerView.Adapter<AdapterListCategories.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataCourseList) {
            binding.nameCategories.text = item.category
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
            ItemCardCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterServiceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}