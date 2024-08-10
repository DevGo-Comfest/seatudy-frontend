package com.comfest.instructor.ui.sylabus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfest.instructor.data.dummy.SyllabusMaterialInstructor
import com.comfest.seatudy.databinding.ItemSyllabusMaterialInstructorBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class SyllabusMaterialInstructorAdapter: RecyclerView.Adapter<SyllabusMaterialInstructorAdapter.SyllabusMaterialInstructorViewHolder>() {

    private var syllabusMaterial: List<SyllabusMaterialInstructor> = emptyList()

    fun setSyllabusMaterial(newSyllabusMaterial: List<SyllabusMaterialInstructor>) {
        syllabusMaterial = newSyllabusMaterial
        notifyDataSetChanged()
    }

    inner class SyllabusMaterialInstructorViewHolder(private val binding: ItemSyllabusMaterialInstructorBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(syllabusMaterial: SyllabusMaterialInstructor) {
            binding.apply {
                tvTitleSyllabusMaterial.text = syllabusMaterial.title
                tvDescSyllabusMaterial.text = syllabusMaterial.desc

                val videoId = extractYoutubeVideoId(syllabusMaterial.linkMaterial)

                youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        videoId?.let { youTubePlayer.cueVideo(it, 0f) }
                    }
                })

                (binding.root.context as? androidx.lifecycle.LifecycleOwner)?.lifecycle?.addObserver(youtubePlayerView)
            }



        }

        fun releaseYoutubePlayer() {
            binding.youtubePlayerView.release()
        }

        private fun extractYoutubeVideoId(url: String): String? {
            val pattern = "^(?:https?:\\/\\/)?(?:www\\.)?(?:youtube\\.com\\/watch\\?v=|youtu\\.be\\/)([\\w-]{11})".toRegex()
            val matchResult = pattern.find(url)
            return matchResult?.groupValues?.get(1)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SyllabusMaterialInstructorAdapter.SyllabusMaterialInstructorViewHolder {
        val binding = ItemSyllabusMaterialInstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SyllabusMaterialInstructorViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SyllabusMaterialInstructorAdapter.SyllabusMaterialInstructorViewHolder,
        position: Int
    ) {
        holder.bind(syllabusMaterial[position])
    }

    override fun getItemCount(): Int = syllabusMaterial.size


    override fun onViewRecycled(holder: SyllabusMaterialInstructorViewHolder) {
        super.onViewRecycled(holder)
        holder.releaseYoutubePlayer()
    }
}