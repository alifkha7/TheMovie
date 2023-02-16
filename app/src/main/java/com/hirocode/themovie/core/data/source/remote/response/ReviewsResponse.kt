package com.hirocode.themovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(

    @field:SerializedName("id")
	val id: Int,

    @field:SerializedName("page")
	val page: Int,

    @field:SerializedName("total_pages")
	val totalPages: Int,

    @field:SerializedName("results")
	val results: List<ReviewsItem>,

    @field:SerializedName("total_results")
	val totalResults: Int
)