package com.comfest.seatudy.ui.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.comfest.seatudy.R
import com.comfest.seatudy.data.source.respon.ResponseCourses
import com.comfest.seatudy.databinding.ItemCardCategoriesBinding

class AdapterListCategories(
    private val items: List<ResponseCourses>,
    private val onCategoryClick: (name: String) -> Unit
) :
    RecyclerView.Adapter<AdapterListCategories.AdapterServiceViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class AdapterServiceViewHolder(private val binding: ItemCardCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseCourses, isSelected: Boolean) {
            binding.nameCategories.text = item.category

            val selectedBackgroundColor = ContextCompat.getColor(itemView.context, R.color.orange)
            val defaultBackgroundColor = Color.WHITE
            val selectedTextColor = ContextCompat.getColor(itemView.context, R.color.white)
            val defaultTextColor = Color.BLACK

            binding.card.setBackgroundColor(if (isSelected) selectedBackgroundColor else defaultBackgroundColor)
            binding.nameCategories.setTextColor(if (isSelected) selectedTextColor else defaultTextColor)

            itemView.setOnClickListener {
                onCategoryClick(item.category)
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterServiceViewHolder {
        val binding =
            ItemCardCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterServiceViewHolder, position: Int) {
        holder.bind(items[position], position == selectedPosition)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}