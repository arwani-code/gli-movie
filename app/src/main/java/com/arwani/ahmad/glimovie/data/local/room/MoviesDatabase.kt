package com.arwani.ahmad.glimovie.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arwani.ahmad.glimovie.data.local.entity.Movie
import com.arwani.ahmad.glimovie.data.local.entity.RemoteKeys


@Database(
    entities = [Movie::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}