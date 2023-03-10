package com.hirocode.themovie.core.domain.usecase

import com.hirocode.themovie.core.domain.repository.IDiscoverRepository

class DiscoverInteractor(private val discoverRepository: IDiscoverRepository): DiscoverUseCase {
    override fun getDiscover(genreId: Int?) = discoverRepository.getDiscover(genreId)

}