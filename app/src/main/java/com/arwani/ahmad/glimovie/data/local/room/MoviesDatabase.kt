package com.arwani.ahmad.glimovie.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arwani.ahmad.glimovie.data.local.entity.Converters
import com.arwani.ahmad.glimovie.data.local.entity.GenreMovies
import com.arwani.ahmad.glimovie.data.local.entity.Movie
import com.arwani.ahmad.glimovie.data.local.entity.RemoteKeys


@Database(
    entities = [Movie::class, RemoteKeys::class, GenreMovies::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}