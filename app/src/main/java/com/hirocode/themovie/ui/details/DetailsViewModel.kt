package com.hirocode.themovie.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hirocode.themovie.core.domain.model.Reviews
import com.hirocode.themovie.core.domain.model.Videos
import com.hirocode.themovie.core.domain.usecase.ReviewsUseCase
import com.hirocode.themovie.core.domain.usecase.VideosUseCase

class DetailsViewModel(private val reviewsUseCase: ReviewsUseCase, private val videosUseCase: VideosUseCase) : ViewModel() {
    lateinit var reviews: LiveData<PagingData<Reviews>>

    fun getReview(movieId: Int?) {
        reviews = reviewsUseCase.getReviews(movieId).cachedIn(viewModelScope).asLiveData()
    }

    lateinit var videos: LiveData<List<Videos>>

    fun getVideos(movieId: Int?) {
        videos = videosUseCase.getVideos(movieId).asLiveData()
    }
}