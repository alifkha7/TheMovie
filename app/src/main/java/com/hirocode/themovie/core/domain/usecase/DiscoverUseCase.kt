package com.hirocode.themovie.core.domain.usecase

import androidx.paging.PagingData
import com.hirocode.themovie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DiscoverUseCase {
    fun getGenres(genreId: Int?): Flow<PagingData<Movie>>
}