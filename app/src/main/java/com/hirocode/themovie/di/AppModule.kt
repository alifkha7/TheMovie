package com.hirocode.themovie.di

import com.hirocode.themovie.core.domain.usecase.*
import com.hirocode.themovie.ui.details.DetailsViewModel
import com.hirocode.themovie.ui.discover.DiscoverViewModel
import com.hirocode.themovie.ui.genres.GenresViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GenresUseCase> { GenresInteractor(get()) }
    factory<DiscoverUseCase> { DiscoverInteractor(get()) }
    factory<ReviewsUseCase> { ReviewsInteractor(get()) }
    factory<VideosUseCase> { VideosInteractor(get()) }
}

val viewModelModule = module {
    viewModel { GenresViewModel(get()) }
    viewModel { DiscoverViewModel(get()) }
    viewModel { DetailsViewModel(get(), get()) }
}