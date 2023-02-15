package com.hirocode.themovie.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hirocode.themovie.core.domain.model.Genres
import com.hirocode.themovie.databinding.ItemGenreBinding

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    private var listData = ArrayList<Genres>()

    fun setData(newListData: List<Genres>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ViewHolder(private val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Genres) {
            with(binding) {
                tvItemTitle.text = data.name
            }
        }
    }
}