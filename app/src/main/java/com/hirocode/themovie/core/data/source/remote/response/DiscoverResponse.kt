package com.hirocode.themovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DiscoverResponse(

    @field:SerializedName("page")
	val page: Int,

    @field:SerializedName("total_pages")
	val totalPages: Int,

    @field:SerializedName("results")
	val results: List<MoviesItem>,

    @field:SerializedName("total_results")
	val totalResults: Int
)