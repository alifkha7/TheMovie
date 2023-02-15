package com.hirocode.themovie.core.data.source.remote

import android.util.Log
import com.hirocode.themovie.core.data.source.remote.network.ApiResponse
import com.hirocode.themovie.core.data.source.remote.network.ApiService
import com.hirocode.themovie.core.data.source.remote.response.GenresItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getGenres(): Flow<ApiResponse<List<GenresItem>>> {
        return flow {
            try {
                val response = apiService.getGenres("")
                val dataArray = response.genres
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}