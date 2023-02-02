package com.arwani.ahmad.glimovie.ui.info

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.arwani.ahmad.glimovie.ui.common.UiState

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    infoViewModel: InfoViewModel = hiltViewModel(),
    movieId: Int,
    navigateBack: () -> Unit
) {

    val genres = infoViewModel.getAllGenres().collectAsState(initial = emptyList()).value

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        infoViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> infoViewModel.getMovieById(movieId)
                is UiState.Success -> {
                    val movie = uiState.data
                    genres.map { genreMovies ->
                        movie.genres.map { id ->
                            if (genreMovies.id == id) {
                               Text(text = "${genreMovies.name},")
                            }
                        }
                    }
                }
                is UiState.Error -> {
                    Text(text = uiState.errorMessage)
                }
            }
        }

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
}