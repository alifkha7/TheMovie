package com.hirocode.themovie.di

import com.hirocode.themovie.core.domain.usecase.GenresInteractor
import com.hirocode.themovie.core.domain.usecase.GenresUseCase
import com.hirocode.themovie.ui.genres.GenresViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GenresUseCase> { GenresInteractor(get()) }
}

val viewModelModule = module {
    viewModel { GenresViewModel(get()) }
}