package com.comfest.seatudy.ui.cart.topup

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.comfest.seatudy.databinding.ItemCardTopupBinding
import com.comfest.seatudy.domain.model.DataPayment

class AdapterTopUp(private val items: List<DataPayment>) :
    RecyclerView.Adapter<AdapterTopUp.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardTopupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataPayment) {
            binding.payment.text = item.payment

            Glide.with(itemView)
                .load(item.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.circleImageView)

            binding.cardCourse.setOnClickListener {
                val intent =
                    Intent(itemView.context, VerifyAmountActivity::class.java).apply {
                        putExtra("TITLE", item.payment)
                    }
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterServiceViewHolder {
        val binding =
            ItemCardTopupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterServiceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}