package com.comfest.seatudy.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.seatudy.databinding.ItemCardCategoriesBinding

class AdapterListCategories(
    private val items: List<String>,
    private val onCategoryClick: (name: String) -> Unit
) :
    RecyclerView.Adapter<AdapterListCategories.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.nameCategories.text = item
            itemView.setOnClickListener {
                onCategoryClick(item)
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