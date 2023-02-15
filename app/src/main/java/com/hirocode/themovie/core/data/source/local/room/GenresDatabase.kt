package com.hirocode.themovie.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hirocode.themovie.core.data.source.local.entity.GenresEntity

@Database(entities = [GenresEntity::class], version = 1, exportSchema = false)
abstract class GenresDatabase : RoomDatabase() {
    abstract fun genresDao() : GenresDao
}