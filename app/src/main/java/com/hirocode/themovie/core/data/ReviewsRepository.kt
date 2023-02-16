package com.hirocode.themovie.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hirocode.themovie.core.data.source.remote.ReviewsPagingSource
import com.hirocode.themovie.core.data.source.remote.network.ApiService
import com.hirocode.themovie.core.domain.model.Reviews
import com.hirocode.themovie.core.domain.repository.IReviewsRepository
import com.hirocode.themovie.core.utils.ReviewsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReviewsRepository(
    private val apiService: ApiService,
) : IReviewsRepository {

    override fun getReviews(movieId: Int?): Flow<PagingData<Reviews>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
            ),
            pagingSourceFactory = {
                ReviewsPagingSource(apiService, movieId)
            }
        ).flow.map { pagingData -> pagingData.map { ReviewsMapper.mapResponsesToDomain(it) } }
    }
}