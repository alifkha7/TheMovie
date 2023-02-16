package com.hirocode.themovie.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hirocode.themovie.core.domain.model.Videos
import com.hirocode.themovie.databinding.ItemVideosBinding
import com.hirocode.themovie.ui.videos.VideosActivity

class VideosAdapter : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    private var listData = ArrayList<Videos>()

    fun setData(newListData: List<Videos>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ViewHolder(private val binding: ItemVideosBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Videos) {
            Glide.with(itemView.context)
                .load("https://img.youtube.com/vi/" + data.key + "/hqdefault.jpg")
                .centerCrop()
                .into(binding.thumbnail)
            itemView.setOnClickListener {
                val videos = Intent(itemView.context, VideosActivity::class.java)
                videos.putExtra("videos", data)
                itemView.context.startActivity(videos)
            }
        }
    }
}