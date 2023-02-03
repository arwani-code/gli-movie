package com.arwani.ahmad.glimovie

import com.arwani.ahmad.glimovie.data.local.entity.GenreMovies
import com.arwani.ahmad.glimovie.data.local.entity.Movie
import com.arwani.ahmad.glimovie.data.network.response.*

object DataDummy {

    fun authorDetails(): AuthorDetails =
        AuthorDetails(name = "slkms", avatar_path = "slmd", rating = 2.3, username = "osd")

    fun result(): List<Result> {
        val sknd = ArrayList<Result>()
        for (i in 1..10) {
            val dkn = Result(
                author = "skmds",
                id = "skds",
                author_details = authorDetails(),
                content = "sldm",
                created_at = "slmd",
                updated_at = "slmd",
                url = "sdoe"
            )
            sknd.add(dkn)
        }
        return sknd
    }

    fun reviewMovies(): ReviewMovies = ReviewMovies(
        results = result(),
        page = 1,
        id = 1,
        total_pages = 1,
        total_results = 1
    )

    fun videos(): List<Video> {
        val videoMovie = ArrayList<Video>()
        for (i in 1..10) {
            val video = Video(
                name = "skmdksd",
                id = "skmdksd",
                key = "skmdksd",
                iso_639_1 = "sklmdksdmskdoi",
                iso_3166_1 = "skdksdns",
                official = false,
                published_at = "skmdksd",
                site = "slmdsd",
                size = i,
                type = "skmdksd"
            )
            videoMovie.add(video)
        }
        return videoMovie
    }

    fun genres(): List<Genre> {
        val genreMovies = ArrayList<Genre>()
        for (i in 1..10) {
            val genre = Genre(
                id = i,
                name = "sjnd"
            )
            genreMovies.add(genre)
        }
        return genreMovies
    }

    fun listMovies(): List<Movie> {
        val movies = ArrayList<Movie>()
        for (i in 1..20) {
            val movie = Movie(
                id = i,
                page = i,
                title = "Testst",
                popularity = 3.2,
                releaseDate = "1000-09-01",
                posterPath = "skdmskd",
                voteCount = i,
                voteAverage = 3.4,
                genres = listOf(i),
                isFavorite = false,
                ogTitle = "sd",
                overview = "skmdksd"
            )
            movies.add(movie)
        }
        return movies
    }

    fun dataMovieResponse(): MovieResponse =
        MovieResponse(page = 1, movies = listMovies(), totalPages = 1, totalResults = 2)

    fun generateGenresMovies(): GenresResponse = GenresResponse(genres = genres())

    fun generateVideoResponse(): VideoResponse = VideoResponse(id = 1, results = videos())

    val movie = Movie(
        id = 1,
        page = 1,
        title = "Testst",
        popularity = 3.2,
        releaseDate = "1000-09-01",
        posterPath = "skdmskd",
        voteCount = 1,
        voteAverage = 3.4,
        genres = listOf(1),
        isFavorite = false,
        ogTitle = "sd",
        overview = "skmdksd"
    )

}