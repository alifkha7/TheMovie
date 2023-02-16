package com.hirocode.themovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class VideosResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("results")
	val results: List<VideosItem>
)