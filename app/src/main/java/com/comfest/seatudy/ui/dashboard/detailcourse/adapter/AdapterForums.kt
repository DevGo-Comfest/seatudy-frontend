package com.comfest.seatudy.ui.dashboard.detailcourse.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.seatudy.data.source.respon.ResponseForumDetail
import com.comfest.seatudy.databinding.ItemCardForumBinding

class AdapterForums(private val items: List<ResponseForumDetail>) :
    RecyclerView.Adapter<AdapterForums.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardForumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseForumDetail) {
            binding.tvNameCourse.text = item.userName
            binding.tvDescription.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterServiceViewHolder {
        val binding =
            ItemCardForumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterServiceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}