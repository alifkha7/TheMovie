package com.hirocode.themovie.di

import com.hirocode.themovie.core.domain.usecase.GenresInteractor
import com.hirocode.themovie.core.domain.usecase.GenresUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<GenresUseCase> { GenresInteractor(get()) }
}