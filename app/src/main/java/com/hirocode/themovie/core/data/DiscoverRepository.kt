package com.hirocode.themovie.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hirocode.themovie.core.data.source.remote.DiscoverPagingSource
import com.hirocode.themovie.core.data.source.remote.network.ApiService
import com.hirocode.themovie.core.domain.model.Movie
import com.hirocode.themovie.core.domain.repository.IDiscoverRepository
import com.hirocode.themovie.core.utils.DiscoverMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DiscoverRepository(
    private val apiService: ApiService,
) : IDiscoverRepository {

    override fun getDiscover(genreId: Int?): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
            ),
            pagingSourceFactory = {
                DiscoverPagingSource(apiService, genreId)
            }
        ).flow.map { pagingData -> pagingData.map { DiscoverMapper.mapResponsesToDomain(it) } }
    }
}