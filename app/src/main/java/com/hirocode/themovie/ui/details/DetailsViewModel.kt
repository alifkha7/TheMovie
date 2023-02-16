package com.hirocode.themovie.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hirocode.themovie.core.domain.model.Reviews
import com.hirocode.themovie.core.domain.usecase.ReviewsUseCase

class DetailsViewModel(private val reviewsUseCase: ReviewsUseCase) : ViewModel() {
    lateinit var reviews: LiveData<PagingData<Reviews>>

    fun getReview(movieId: Int?) {
        reviews = reviewsUseCase.getReviews(movieId).cachedIn(viewModelScope).asLiveData()
    }
}