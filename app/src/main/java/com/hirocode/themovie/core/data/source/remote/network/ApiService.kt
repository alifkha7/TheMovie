package com.hirocode.themovie.core.data.source.remote.network

import com.hirocode.themovie.core.data.source.remote.response.DiscoverResponse
import com.hirocode.themovie.core.data.source.remote.response.GenresResponse
import com.hirocode.themovie.core.data.source.remote.response.ReviewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") api_key: String
    ): GenresResponse

    @GET("discover/movie")
    suspend fun getDiscover(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
        @Query("with_genres") with_genres: Int?,
    ): DiscoverResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movie_id: Int?,
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): ReviewsResponse
}