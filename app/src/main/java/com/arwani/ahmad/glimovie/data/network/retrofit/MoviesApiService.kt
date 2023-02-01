package com.arwani.ahmad.glimovie.data.network.retrofit

import com.arwani.ahmad.glimovie.data.network.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("movie/popular?api_key=52a7bcf7b8476e7ec647f65e0916b417&language=en-US")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MovieResponse

}