package com.hirocode.themovie.core.domain.usecase

import com.hirocode.themovie.core.data.Resource
import com.hirocode.themovie.core.domain.model.Genres
import kotlinx.coroutines.flow.Flow

interface GenresUseCase {
    fun getGenres(): Flow<Resource<List<Genres>>>
}