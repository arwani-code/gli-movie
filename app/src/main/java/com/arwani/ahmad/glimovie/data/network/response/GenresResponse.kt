package com.arwani.ahmad.glimovie.data.network.response

data class GenresResponse(
    val genres: List<Genre>
)

data class Genre(
    val id: Int,
    val name: String
)