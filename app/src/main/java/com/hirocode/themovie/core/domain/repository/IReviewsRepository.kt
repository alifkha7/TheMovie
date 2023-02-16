package com.hirocode.themovie.core.domain.repository

import androidx.paging.PagingData
import com.hirocode.themovie.core.domain.model.Reviews
import kotlinx.coroutines.flow.Flow

interface IReviewsRepository {
    fun getReviews(movieId: Int?): Flow<PagingData<Reviews>>
}