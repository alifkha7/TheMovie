package com.hirocode.themovie.ui.details

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hirocode.themovie.core.domain.model.Movie
import com.hirocode.themovie.core.ui.LoadingStateAdapter
import com.hirocode.themovie.core.ui.ReviewsAdapter
import com.hirocode.themovie.core.ui.VideosAdapter
import com.hirocode.themovie.core.utils.BASE_IMAGE_URL
import com.hirocode.themovie.databinding.ActivityDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.StrictMath.abs

class DetailsActivity : AppCompatActivity() {

    private val detailsViewModel : DetailsViewModel by viewModel()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("detail", Movie::class.java)
        } else {
            intent.getParcelableExtra("detail")
        }

        setDetail(movie)
        getReviews(movie)
        getVideos(movie)
    }

    private fun setDetail(movie: Movie?) {
        binding.appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                binding.toolbarLayout.title = movie?.title
            } else {
                binding.toolbarLayout.title = ""
            }
        }
        Glide.with(this)
            .load(BASE_IMAGE_URL + movie?.backdropPath)
            .centerCrop()
            .into(binding.backdrop)
        Glide.with(this)
            .load(BASE_IMAGE_URL + movie?.posterPath)
            .centerCrop()
            .into(binding.poster)
        binding.title.text = movie?.title
        if (movie != null) {
            binding.vote.rating = (movie.voteAverage / 2).toFloat()
        }
        binding.overview.text = movie?.overview
    }

    private fun getReviews(movie: Movie?) {
        binding.rvReview.layoutManager = LinearLayoutManager(this)
        val adapter = ReviewsAdapter()
        binding.rvReview.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        adapter.addLoadStateListener { loadState ->
            binding.apply {
                rvReview.isVisible = loadState.refresh is LoadState.NotLoading

                if (loadState.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter.itemCount < 1) {
                    rvReview.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
        detailsViewModel.getReview(movie?.id)
        detailsViewModel.reviews.observe(this) { reviews ->
            adapter.submitData(lifecycle, reviews)
        }
    }

    private fun getVideos(movie: Movie?) {
        binding.rvVideos.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = VideosAdapter()
        binding.rvVideos.adapter = adapter
        detailsViewModel.getVideos(movie?.id)
        detailsViewModel.videos.observe(this) { videos ->
            adapter.setData(listOf(videos.first { it.type.contains("Trailer") }))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}