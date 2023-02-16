package com.hirocode.themovie.core.data.source.remote

import android.util.Log
import com.hirocode.themovie.core.data.source.remote.network.ApiService
import com.hirocode.themovie.core.data.source.remote.response.VideosItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class VideosDataSource(private val apiService: ApiService) {
    fun getVideos(movieId: Int?): Flow<List<VideosItem>> {
        return flow {
            try {
                val response = apiService.getVideos(movieId, "6d754783e28255649713de5092888da4")
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(response.results)
                }
            } catch (e : Exception){
                Log.e("VideosDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}