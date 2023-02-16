package com.hirocode.themovie.ui.details

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hirocode.themovie.core.domain.model.Movie
import com.hirocode.themovie.databinding.ActivityDetailsBinding
import java.lang.StrictMath.abs

class DetailsActivity : AppCompatActivity() {

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
            .load("https://image.tmdb.org/t/p/original" + movie?.backdropPath)
            .centerCrop()
            .into(binding.backdrop)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original" + movie?.posterPath)
            .centerCrop()
            .into(binding.poster)
        binding.title.text = movie?.title
        if (movie != null) {
            binding.vote.rating = (movie.voteAverage / 2).toFloat()
        }
        binding.overview.text = movie?.overview
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}