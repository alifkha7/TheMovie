package com.hirocode.themovie.core.domain.repository

import com.hirocode.themovie.core.domain.model.Videos
import kotlinx.coroutines.flow.Flow

interface IVideosRepository {
    fun getVideos(movieId: Int?): Flow<List<Videos>>
}