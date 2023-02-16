package com.hirocode.themovie.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hirocode.themovie.core.domain.model.Reviews
import com.hirocode.themovie.databinding.ItemReviewBinding

class ReviewsAdapter :
    PagingDataAdapter<Reviews, ReviewsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class ViewHolder (private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Reviews) {
            binding.tvItemAuthor.text = data.author
            binding.tvItemContent.text = data.content
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Reviews>() {
            override fun areItemsTheSame(oldItem: Reviews, newItem: Reviews): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Reviews, newItem: Reviews): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}