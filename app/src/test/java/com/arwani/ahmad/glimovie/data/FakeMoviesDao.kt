package com.arwani.ahmad.glimovie.data

import androidx.paging.PagingSource
import com.arwani.ahmad.glimovie.data.local.entity.GenreMovies
import com.arwani.ahmad.glimovie.data.local.entity.Movie
import com.arwani.ahmad.glimovie.data.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMoviesDao : MoviesDao {

    private var moviesData = mutableListOf<Movie>()
    private var moviesGenres = mutableListOf<GenreMovies>()

    override suspend fun insertAll(movies: List<Movie>) {
        moviesData.addAll(movies)
    }

    override suspend fun insertGenres(movies: List<GenreMovies>) {
        moviesGenres.addAll(movies)
    }

    override suspend fun updateFavorite(movieId: Int, favorite: Boolean) {
        moviesData.isNotEmpty()
    }

    override suspend fun deleteGenres(genreMovies: List<GenreMovies>) {
        moviesGenres.removeAll(genreMovies)
    }

    override fun getAllGenres(): Flow<List<GenreMovies>> {
        return flow { emit(moviesGenres) }
    }

    override fun getMovies(): PagingSource<Int, Movie> {
        TODO("Not yet implemented")
    }

    override fun getMovieId(id: Int): Flow<Movie> {
        return flow { emit(moviesData.first { it.isFavorite }) }
    }

    override suspend fun clearAllMovies() {
        moviesData.removeLastOrNull()
    }

    override fun searchPosts(name: String): Flow<List<Movie>> {
        return flow { emit(moviesData.filter { it.title == name }) }
    }

    override fun getFavorites(): Flow<List<Movie>> {
        return flow { emit(moviesData.filter { it.isFavorite }) }
    }
}