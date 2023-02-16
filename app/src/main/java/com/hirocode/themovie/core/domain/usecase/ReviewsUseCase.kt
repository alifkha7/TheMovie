package com.hirocode.themovie.core.domain.usecase

import androidx.paging.PagingData
import com.hirocode.themovie.core.domain.model.Reviews
import kotlinx.coroutines.flow.Flow

interface ReviewsUseCase {
    fun getReviews(movieId: Int?): Flow<PagingData<Reviews>>
}