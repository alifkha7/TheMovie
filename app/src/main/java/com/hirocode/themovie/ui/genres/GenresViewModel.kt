package com.hirocode.themovie.ui.genres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hirocode.themovie.core.domain.usecase.GenresUseCase

class GenresViewModel(genresUseCase: GenresUseCase) : ViewModel() {
    val genres = genresUseCase.getGenres().asLiveData()
}