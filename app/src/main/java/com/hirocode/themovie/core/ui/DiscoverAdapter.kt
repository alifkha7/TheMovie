package com.hirocode.themovie.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hirocode.themovie.R
import com.hirocode.themovie.core.domain.model.Movie
import com.hirocode.themovie.databinding.ItemDiscoverBinding

class DiscoverAdapter :
    PagingDataAdapter<Movie, DiscoverAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDiscoverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class ViewHolder(private val binding: ItemDiscoverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/original" + data.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .centerCrop()
                .into(binding.imgItemPoster)
            binding.tvItemTitle.text = data.title
            binding.rbItemVote.rating = (data.voteAverage / 2).toFloat()
//            itemView.setOnClickListener {
//                val detail = Intent(itemView.context, DetailsActivity::class.java)
//                detail.putExtra("detail", data)
//                itemView.context.startActivity(detail)
//            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}