package com.arwani.ahmad.glimovie.di

import android.content.Context
import androidx.room.Room
import com.arwani.ahmad.glimovie.data.local.room.MoviesDao
import com.arwani.ahmad.glimovie.data.local.room.MoviesDatabase
import com.arwani.ahmad.glimovie.data.local.room.RemoteKeysDao
import com.arwani.ahmad.glimovie.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            Constant.MOVIE_DB
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(moviesDatabase: MoviesDatabase): MoviesDao = moviesDatabase.getMoviesDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(moviesDatabase: MoviesDatabase): RemoteKeysDao = moviesDatabase.getRemoteKeysDao()
}