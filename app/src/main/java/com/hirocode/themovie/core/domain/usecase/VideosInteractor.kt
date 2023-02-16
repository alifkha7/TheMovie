package com.hirocode.themovie.core.domain.usecase

import com.hirocode.themovie.core.domain.repository.IVideosRepository

class VideosInteractor(private val videosRepository: IVideosRepository): VideosUseCase {
    override fun getVideos(movieId: Int?) = videosRepository.getVideos(movieId)

}