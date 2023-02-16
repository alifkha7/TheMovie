package com.hirocode.themovie.core.domain.usecase

import com.hirocode.themovie.core.domain.repository.IReviewsRepository

class ReviewsInteractor(private val reviewsRepository: IReviewsRepository): ReviewsUseCase {
    override fun getReviews(movieId: Int?) = reviewsRepository.getReviews(movieId)

}