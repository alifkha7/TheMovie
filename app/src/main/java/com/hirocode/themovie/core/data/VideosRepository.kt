package com.hirocode.themovie.core.data

import com.hirocode.themovie.core.data.source.remote.VideosDataSource
import com.hirocode.themovie.core.domain.model.Videos
import com.hirocode.themovie.core.domain.repository.IVideosRepository
import com.hirocode.themovie.core.utils.VideosMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VideosRepository(
    private val videosDataSource: VideosDataSource,
) : IVideosRepository {

    override fun getVideos(movieId: Int?): Flow<List<Videos>> {
        return videosDataSource.getVideos(movieId).map {
            VideosMapper.mapResponsesToDomain(it)
        }
    }
}