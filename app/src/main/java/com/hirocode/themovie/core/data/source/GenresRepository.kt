package com.hirocode.themovie.core.data.source

import com.hirocode.movieapp.core.data.Resource
import com.hirocode.themovie.core.data.source.local.LocalDataSource
import com.hirocode.themovie.core.data.source.remote.RemoteDataSource
import com.hirocode.themovie.core.data.source.remote.network.ApiResponse
import com.hirocode.themovie.core.data.source.remote.response.GenresItem
import com.hirocode.themovie.core.domain.model.Genres
import com.hirocode.themovie.core.domain.repository.IGenresRepository
import com.hirocode.themovie.core.utils.GenresMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GenresRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IGenresRepository {

    override fun getGenres(): Flow<Resource<List<Genres>>> =
        object : NetworkBoundResource<List<Genres>, List<GenresItem>>() {
            override fun loadFromDB(): Flow<List<Genres>> {
                return localDataSource.getGenres().map { GenresMapper.mapEntitiesToDomain(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GenresItem>>> =
                remoteDataSource.getGenres()

            override suspend fun saveCallResult(data: List<GenresItem>) {
                val genresList = GenresMapper.mapResponsesToEntities(data)
                localDataSource.insertGenres(genresList)
            }

            override fun shouldFetch(data: List<Genres>?): Boolean = data == null || data.isEmpty()

        }.asFlow()
}