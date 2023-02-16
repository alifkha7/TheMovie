package com.hirocode.themovie.core.utils

import com.hirocode.themovie.core.data.source.remote.response.ReviewsItem
import com.hirocode.themovie.core.domain.model.Reviews

object ReviewsMapper {
    fun mapResponsesToDomain(input: ReviewsItem) = Reviews(
        author = input.author,
        id = input.id,
        content = input.content,
    )
}