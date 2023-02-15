package com.hirocode.themovie.core.domain.repository

import com.hirocode.movieapp.core.data.Resource
import com.hirocode.themovie.core.domain.model.Genres
import kotlinx.coroutines.flow.Flow

interface IGenresRepository {
    fun getGenres(): Flow<Resource<List<Genres>>>
}