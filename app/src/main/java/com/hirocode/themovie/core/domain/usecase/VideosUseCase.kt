package com.hirocode.themovie.core.domain.usecase

import com.hirocode.themovie.core.domain.model.Videos
import kotlinx.coroutines.flow.Flow

interface VideosUseCase {
    fun getVideos(movieId: Int?): Flow<List<Videos>>
}