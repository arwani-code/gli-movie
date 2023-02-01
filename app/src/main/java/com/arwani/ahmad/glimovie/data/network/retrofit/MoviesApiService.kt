package com.arwani.ahmad.glimovie.data.network.retrofit

import com.arwani.ahmad.glimovie.data.network.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET("movie/{playing}?api_key=52a7bcf7b8476e7ec647f65e0916b417&language=en-US")
    suspend fun getPopularMovies(
        @Path("playing") playing: String,
        @Query("page") page: Int
    ): MovieResponse

}