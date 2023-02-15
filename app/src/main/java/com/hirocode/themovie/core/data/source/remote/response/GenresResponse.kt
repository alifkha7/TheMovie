package com.hirocode.themovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenresResponse(

	@field:SerializedName("genres")
	val genres: List<GenresItem>
)