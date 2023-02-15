package com.hirocode.themovie.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hirocode.themovie.core.data.source.local.entity.GenresEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenresDao {
    @Query("SELECT * FROM genres")
    fun getGenres(): Flow<List<GenresEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenresEntity>)
}