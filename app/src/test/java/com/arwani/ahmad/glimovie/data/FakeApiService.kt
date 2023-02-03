package com.arwani.ahmad.glimovie.data

import com.arwani.ahmad.glimovie.DataDummy
import com.arwani.ahmad.glimovie.data.network.response.GenresResponse
import com.arwani.ahmad.glimovie.data.network.response.MovieResponse
import com.arwani.ahmad.glimovie.data.network.response.ReviewMovies
import com.arwani.ahmad.glimovie.data.network.response.VideoResponse
import com.arwani.ahmad.glimovie.data.network.retrofit.MoviesApiService

class FakeApiService : MoviesApiService {

    override suspend fun getPopularMovies(playing: String, page: Int): MovieResponse {
        return DataDummy.dataMovieResponse()
    }

    override suspend fun getGenreMovies(): GenresResponse {
        return DataDummy.generateGenresMovies()
    }

    override suspend fun getVideoMovies(movieId: Int): VideoResponse {
        return DataDummy.generateVideoResponse()
    }

    override suspend fun getReviewMovies(movieId: Int): ReviewMovies {
        return DataDummy.reviewMovies()
    }
}