package com.comfest.seatudy.ui.dashboard.detailcourse.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.seatudy.data.source.respon.ResponseSyllabuses
import com.comfest.seatudy.databinding.ItemCardSyllabusBinding
import com.comfest.seatudy.ui.dashboard.detailpersyllabus.CourseDetailPerSyllabusActivity

class AdapterCourseProgram(private val items: List<ResponseSyllabuses>, val locked: Boolean, val size: Int) :
    RecyclerView.Adapter<AdapterCourseProgram.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardSyllabusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseSyllabuses) {
            binding.tvNameCourse.text = item.title

            Log.d("CEK LOCKED1", "${item.isLocked}")
            if(locked) {
                Log.d("CEK LOCKED2", "${item.isLocked}")
                if (item.isLocked) {
                    binding.imgCourse.visibility = View.VISIBLE
                } else {
                    binding.imgCourse.visibility = View.GONE
                    binding.cardCourse.setOnClickListener {
                        val intent =
                            Intent(
                                itemView.context,
                                CourseDetailPerSyllabusActivity::class.java
                            ).apply {
                                putExtra("ID", item.syllabusID.toString())
                                putExtra("SIZE", size.toString())
                                putExtra("TITLE", item.title)
                                putExtra("DESCRIPTION", item.description)
                            }
                        itemView.context.startActivity(intent)
                    }
                }
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