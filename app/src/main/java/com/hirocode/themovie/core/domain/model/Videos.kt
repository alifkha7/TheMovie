package com.hirocode.themovie.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Videos(
	val id: String,
	val type: String,
	val key: String
) : Parcelable