package com.arwani.ahmad.glimovie.ui.info

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arwani.ahmad.glimovie.ui.common.UiState
import com.arwani.ahmad.glimovie.ui.theme.Green100

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    infoViewModel: InfoViewModel = hiltViewModel(),
    movieId: Int,
    navigateBack: () -> Unit
) {

    val genres = infoViewModel.getAllGenres().collectAsState(initial = emptyList()).value

    LazyColumn() {
        item {
            Title(title = "Movie Info")
            infoViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> infoViewModel.getMovieById(movieId)
                    is UiState.Success -> {
                        val movie = uiState.data
                        genres.map { genreMovies ->
                            movie.genres.map { id ->
                                if (genreMovies.id == id) {

                                }
                            }
                        }
                        MovieInfo(
                            genre = " ,",
                            voteAverage = movie.voteAverage.toString(),
                            voteCount = movie.voteCount.toString(),
                            title = movie.title,
                            posterPath = movie.posterPath.toString(),
                            releaseDate = movie.releaseDate.toString(),
                            popularity = movie.popularity.toString()
                        )
                        Divider(
                            startIndent = 50.dp,
                            color = Green100,
                            modifier = modifier.padding(end = 50.dp)
                        )
                    }
                    is UiState.Error -> {
                        Text(text = uiState.errorMessage)
                    }
                }
            }
        }

        item {
            infoViewModel.videoUiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> infoViewModel.getMovieVideo(movieId)
                    is UiState.Success -> {
                        val movieVideo = uiState.data
                    }
                    is UiState.Error -> {
                        Text(text = uiState.errorMessage)
                    }
                }
            }
        }

        item {
            infoViewModel.reviewMovies.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> infoViewModel.getReviewMovies(movieId)
                    is UiState.Success -> {
                        val reviews = uiState.data
                    }
                    is UiState.Error -> {
                        Text(text = uiState.errorMessage)
                    }
                }
            }
        }

        item {
            LazyRow {

            }
        }
    }
}

@Composable
fun Title(modifier: Modifier = Modifier, title: String){
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = modifier.padding(start = 15.dp, top = 16.dp)
    )
}