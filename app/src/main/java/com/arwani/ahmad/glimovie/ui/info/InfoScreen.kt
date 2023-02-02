package com.arwani.ahmad.glimovie.ui.info

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arwani.ahmad.glimovie.data.network.response.Result
import com.arwani.ahmad.glimovie.ui.common.UiState
import com.arwani.ahmad.glimovie.ui.components.TopMovieBar
import com.arwani.ahmad.glimovie.ui.theme.Green100

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    infoViewModel: InfoViewModel = hiltViewModel(),
    movieId: Int,
    navigateBack: () -> Unit
) {
    val genres by infoViewModel.series.collectAsState()
    infoViewModel.getAllGenres()
    Scaffold(
        topBar = {
            Column {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.primary)
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { navigateBack() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "arrow_back",
                                    tint = Color.White
                                )
                            }
                        Text(
                            modifier = modifier.padding(horizontal = 8.dp, vertical = 12.dp),
                            text = "Detail",
                            style = MaterialTheme.typography.h6,
                            color = Color.White
                        )
                    }
                }
                Divider(color = Color.White.copy(0.2f))
            }
        },
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary
    ) { paddingValues ->
        infoViewModel.reviewMovies.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> infoViewModel.getReviewMovies(movieId)
                is UiState.Success -> {
                    val reviews = uiState.data
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                        modifier = modifier.padding(paddingValues)
                    ) {
                        item {
                            infoViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                                when (uiState) {
                                    is UiState.Loading -> {
                                        CircularProgressIndicator()
                                        infoViewModel.getMovieById(movieId)
                                    }
                                    is UiState.Success -> {
                                        val movie = uiState.data
                                        Title(title = "Movie Info")
                                        MovieInfo(
                                            genre = genres.joinToString(", ") { it.name },
                                            voteAverage = movie.voteAverage.toString(),
                                            voteCount = movie.voteCount.toString(),
                                            title = movie.title,
                                            posterPath = movie.posterPath.toString(),
                                            releaseDate = movie.releaseDate.toString(),
                                            popularity = movie.popularity.toString()
                                        )
                                        StickDivider()
                                        Title(title = "Overview")
                                        Text(
                                            text = movie.overview,
                                            modifier = modifier.padding(
                                                top = 8.dp,
                                                bottom = 16.dp
                                            ),
                                            color = Color.White
                                        )
                                        StickDivider()
                                    }
                                    is UiState.Error -> {
                                        Text(text = uiState.errorMessage)
                                    }
                                }
                            }
                        }
                        item {
                            Title(title = "Youtube Trailer")
                            infoViewModel.videoUiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                                when (uiState) {
                                    is UiState.Loading -> infoViewModel.getMovieVideo(movieId)
                                    is UiState.Success -> {
                                        val movieVideo = uiState.data
                                        LazyRow(
                                            contentPadding = PaddingValues(
                                                bottom = 16.dp,
                                                top = 12.dp
                                            ),
                                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                                        ) {
                                            items(movieVideo, key = { it.id }) { videos ->
                                                TrailerScreen(videos = videos) {

                                                }
                                            }
                                        }
                                    }
                                    is UiState.Error -> {
                                        Text(text = uiState.errorMessage)
                                    }
                                }
                            }
                            StickDivider()
                            Title(title = "Reviews", modifier = modifier.padding(bottom = 12.dp))
                        }
                        items(reviews) { item: Result ->
                            ReviewsScreen(review = item)
                        }
                    }
                }
                is UiState.Error -> {
                    Text(text = uiState.errorMessage)
                }
            }
        }
    }
}

@Composable
fun Title(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = modifier.padding(top = 16.dp),
        color = Green100
    )
}

@Composable
fun StickDivider(modifier: Modifier = Modifier) {
    Divider(
        startIndent = 50.dp,
        color = Green100,
        modifier = modifier.padding(end = 50.dp)
    )
}