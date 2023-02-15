package com.hirocode.themovie.core.domain.repository

import androidx.paging.PagingData
import com.hirocode.themovie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IDiscoverRepository {
    fun getDiscover(genreId: Int?): Flow<PagingData<Movie>>
}