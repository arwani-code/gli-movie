package com.arwani.ahmad.glimovie.data.repository

import com.arwani.ahmad.glimovie.data.local.entity.GenreMovies
import com.arwani.ahmad.glimovie.data.local.entity.Movie
import com.arwani.ahmad.glimovie.data.local.room.MoviesDao
import com.arwani.ahmad.glimovie.data.network.response.Genre
import com.arwani.ahmad.glimovie.data.network.response.Result
import com.arwani.ahmad.glimovie.data.network.response.Video
import com.arwani.ahmad.glimovie.data.network.retrofit.MoviesApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesApiService: MoviesApiService,
    private val moviesDao: MoviesDao
) {

    fun getFavoriteMovies(): Flow<List<Movie>> = moviesDao.getFavorites()

    suspend fun setFavorite(movieId: Int, favorite: Boolean) =
        moviesDao.updateFavorite(movieId, favorite)

    fun searchPosts(name: String): Flow<List<Movie>> = moviesDao.searchPosts(name)

    fun getGenres(): Flow<List<GenreMovies>> = moviesDao.getAllGenres()

    fun getReviewMovies(movieId: Int): Flow<List<Result>> = flow {
        val getReviews = moviesApiService.getReviewMovies(movieId).results
        emit(getReviews)
    }

    fun getGenresMovies(): Flow<List<GenreMovies>> = flow {
        val getResponse = moviesApiService.getGenreMovies()
        val genres = getResponse.genres
        val moviesGenres = genres.map {
            GenreMovies(
                id = it.id,
                name = it.name
            )
        }
        moviesDao.deleteGenres(moviesGenres)
        moviesDao.insertGenres(moviesGenres)
    }

    fun getVideoMovies(movieId: Int): Flow<List<Video>> = flow {
        val getVideoResponse = moviesApiService.getVideoMovies(movieId)
        val videoMovies = getVideoResponse.results
        emit(videoMovies)
    }

    fun getMovieId(id: Int): Flow<Movie> = moviesDao.getMovieId(id)
}