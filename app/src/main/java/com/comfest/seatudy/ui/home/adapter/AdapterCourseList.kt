package com.comfest.seatudy.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.comfest.instructor.ui.home.DetailCourseActivity
import com.comfest.seatudy.databinding.ItemCardCourseHomeBinding
import com.comfest.seatudy.domain.model.DataCourseList
import com.comfest.seatudy.ui.dashboard.detail.CourseDetailActivity

class AdapterCourseList(private val items: List<DataCourseList>) :
    RecyclerView.Adapter<AdapterCourseList.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardCourseHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataCourseList) {
            binding.tvNameCourse.text = item.nameCourse
            binding.tvRating.rating = item.rating.toFloat()
            binding.tvHour.text = item.hour + "H"
            binding.tvLevel.text = item.levelCourse
//            Glide.with(itemView)
//                .load(item.)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .centerCrop()
//                .into(binding.imgCourse)

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