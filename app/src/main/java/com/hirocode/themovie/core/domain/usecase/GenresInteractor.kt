package com.hirocode.themovie.core.domain.usecase

import com.hirocode.themovie.core.domain.repository.IGenresRepository

class GenresInteractor(private val genresRepository: IGenresRepository): GenresUseCase {
    override fun getGenres() = genresRepository.getGenres()
}