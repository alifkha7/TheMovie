package com.hirocode.themovie.ui.videos

import android.os.Build
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.hirocode.themovie.BuildConfig.YT_API_KEY
import com.hirocode.themovie.R
import com.hirocode.themovie.core.domain.model.Videos
import com.hirocode.themovie.databinding.ActivityVideosBinding

class VideosActivity : YouTubeBaseActivity() {

    lateinit var binding: ActivityVideosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videos = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("videos", Videos::class.java)
        } else {
            intent.getParcelableExtra("videos")
        }

        binding.youtubePlayer.initialize(YT_API_KEY, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(videos?.key)
                p1?.play()
                p1?.setFullscreen(true)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Snackbar.make(binding.root, getString(R.string.failed_initialize), Snackbar.LENGTH_SHORT).show()
            }

        })
    }
}