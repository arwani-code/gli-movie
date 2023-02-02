package com.arwani.ahmad.glimovie.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arwani.ahmad.glimovie.data.local.entity.GenreMovies
import com.arwani.ahmad.glimovie.data.local.entity.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(movies: List<GenreMovies>)

    @Delete
    suspend fun deleteGenres(genreMovies: List<GenreMovies>)

    @Query("SELECT * FROM genres_movies")
    fun getAllGenres(): Flow<List<GenreMovies>>

    @Query("SELECT * FROM movies ORDER BY page")
    fun getMovies(): PagingSource<Int, Movie>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieId(id: Int): Flow<Movie>

    @Query("DELETE FROM movies")
    suspend fun clearAllMovies()
}