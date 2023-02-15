package com.hirocode.themovie.core.utils

import com.hirocode.themovie.core.data.source.remote.response.MoviesItem
import com.hirocode.themovie.core.domain.model.Movie

object DiscoverMapper {
    fun mapResponsesToDomain(input: MoviesItem) = Movie(
        overview = input.overview,
        title = input.title,
        genreIds = input.genreIds,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        voteAverage = input.voteAverage,
        id = input.id
    )
}