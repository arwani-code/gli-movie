package com.arwani.ahmad.glimovie.data.local.room

import androidx.paging.PagingSource
import androidx.room.*
import com.arwani.ahmad.glimovie.data.local.entity.GenreMovies
import com.arwani.ahmad.glimovie.data.local.entity.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(movies: List<GenreMovies>)

    @Query("UPDATE movies SET favorite = :favorite WHERE id = :movieId")
    suspend fun updateFavorite(movieId: Int, favorite: Boolean)

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

    @Query("SELECT * FROM movies WHERE title LIKE :name")
    fun searchPosts(name: String): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE favorite = 1")
    fun getFavorites(): Flow<List<Movie>>
}