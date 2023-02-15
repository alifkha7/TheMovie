package com.hirocode.themovie.core.utils

import com.hirocode.themovie.core.data.source.local.entity.GenresEntity
import com.hirocode.themovie.core.data.source.remote.response.GenresItem
import com.hirocode.themovie.core.domain.model.Genres

object GenresMapper {
    fun mapResponsesToEntities(input: List<GenresItem>): List<GenresEntity> {
        val genresList = ArrayList<GenresEntity>()
        input.map {
            val genres = GenresEntity(
                id = it.id,
                name = it.name,
            )
            genresList.add(genres)
        }
        return genresList
    }

    fun mapEntitiesToDomain(input: List<GenresEntity>): List<Genres> =
        input.map {
            Genres(
                id = it.id,
                name = it.name,
            )
        }
}