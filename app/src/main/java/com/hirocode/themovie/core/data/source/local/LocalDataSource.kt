package com.hirocode.themovie.core.data.source.local

import com.hirocode.themovie.core.data.source.local.entity.GenresEntity
import com.hirocode.themovie.core.data.source.local.room.GenresDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val genresDao: GenresDao) {
    fun getGenres(): Flow<List<GenresEntity>> = genresDao.getGenres()

    suspend fun insertGenres(genresList: List<GenresEntity>) = genresDao.insertGenres(genresList)
}