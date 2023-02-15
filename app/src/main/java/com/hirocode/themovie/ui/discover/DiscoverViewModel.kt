package com.hirocode.themovie.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hirocode.themovie.core.domain.model.Movie
import com.hirocode.themovie.core.domain.usecase.DiscoverUseCase

class DiscoverViewModel(private val discoverUseCase: DiscoverUseCase) : ViewModel() {
    lateinit var discover: LiveData<PagingData<Movie>>

    fun getGenreId(genreId: Int?) {
        discover = discoverUseCase.getDiscover(genreId).cachedIn(viewModelScope).asLiveData()
    }
}