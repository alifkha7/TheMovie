package com.hirocode.themovie.core.di

import androidx.room.Room
import com.hirocode.themovie.core.data.GenresRepository
import com.hirocode.themovie.core.data.source.local.LocalDataSource
import com.hirocode.themovie.core.data.source.local.room.GenresDatabase
import com.hirocode.themovie.core.data.source.remote.RemoteDataSource
import com.hirocode.themovie.core.data.source.remote.network.ApiService
import com.hirocode.themovie.core.domain.repository.IGenresRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory { get<GenresDatabase>().genresDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GenresDatabase::class.java, "Genres.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IGenresRepository> { GenresRepository(get(), get()) }
}