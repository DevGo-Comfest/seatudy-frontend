package com.comfest.seatudy.ui.dashboard.detailpersyllabus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.comfest.seatudy.data.source.respon.ResponseMaterials
import com.comfest.seatudy.databinding.ItemCardMaterialBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class AdapterMaterial(private val items: List<ResponseMaterials>) :
    RecyclerView.Adapter<AdapterMaterial.AdapterServiceViewHolder>() {

    inner class AdapterServiceViewHolder(private val binding: ItemCardMaterialBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseMaterials) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description

            val videoId = extractYoutubeVideoId(item.urlMaterial)
            binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    videoId?.let {
                        youTubePlayer.cueVideo(it, 0f)
                    }
                }
            })

            (itemView.context as? LifecycleOwner)?.lifecycle?.addObserver(binding.youtubePlayerView)
        }
    }

    private fun extractYoutubeVideoId(url: String): String? {
        val pattern = "^(?:https?://)?(?:www\\.)?(?:youtube\\.com/(?:[^/]+/.+/|(?:v|e(?:mbed)?)|.*[?&]v=)|youtu\\.be/)([\\w-]{11})".toRegex()
        val matchResult = pattern.find(url)
        return matchResult?.groupValues?.get(1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterServiceViewHolder {
        val binding =
            ItemCardMaterialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterServiceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}