package com.hirocode.themovie.core.utils

import com.hirocode.themovie.core.data.source.remote.response.VideosItem
import com.hirocode.themovie.core.domain.model.Videos

object VideosMapper {
    fun mapResponsesToDomain(input: List<VideosItem>): List<Videos> {
        val videosList = ArrayList<Videos>()
        input.map {
            val videos = Videos(
                id = it.id,
                type = it.type,
                key = it.key
            )
            videosList.add(videos)
        }
        return videosList
    }
}