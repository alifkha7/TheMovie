package com.hirocode.themovie.core.data.source.remote.network

import com.hirocode.themovie.core.data.source.remote.response.GenresResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") api_key: String
    ): GenresResponse
}