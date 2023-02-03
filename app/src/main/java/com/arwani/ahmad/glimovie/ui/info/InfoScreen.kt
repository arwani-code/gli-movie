package com.arwani.ahmad.glimovie.ui.info

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arwani.ahmad.glimovie.VideoActivity
import com.arwani.ahmad.glimovie.data.network.response.Result
import com.arwani.ahmad.glimovie.ui.common.UiState
import com.arwani.ahmad.glimovie.ui.theme.Green100
import kotlinx.coroutines.launch

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    infoViewModel: InfoViewModel = hiltViewModel(),
    movieId: Int,
    navigateBack: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()

    var favorite by remember {
        mutableStateOf(false)
    }

    val mContext = LocalContext.current
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    favorite = !favorite
                    coroutineScope.launch {
                        infoViewModel.setFavoriteMovie(movieId, favorite)
                    }
                },
                modifier = modifier.navigationBarsPadding(),
                backgroundColor = Green100
            ) {
                if (favorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "favorite",
                        tint = MaterialTheme.colors.primary
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "favorite_border",
                        tint = MaterialTheme.colors.primary
                    )
                }
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
                                        infoViewModel.getMovieById(movieId)
                                    }
                                    is UiState.Success -> {
                                        val movie = uiState.data
                                        favorite = movie.isFavorite
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
                                        Text(text = uiState.errorMessage, color = Color.White)
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
                                                    val intent =
                                                        Intent(mContext, VideoActivity::class.java)
                                                    intent.putExtra(
                                                        VideoActivity.EXTRA_KEY,
                                                        videos
                                                    )
                                                    mContext.startActivity(intent)
                                                }
                                            }
                                        }
                                    }
                                    is UiState.Error -> {
                                        Text(text = uiState.errorMessage, color = Color.White)
                                    }
                                }
                            }
                            StickDivider()
                            Title(title = "Reviews", modifier = modifier.padding(bottom = 12.dp))
                        }
                        items(reviews, key = { it.id }) { item: Result ->
                            ReviewsScreen(
                                review = item, modifier = Modifier
                                    .padding(vertical = 4.dp)
                            )
                        }
                    }
                }
                is UiState.Error -> {
                    Text(text = uiState.errorMessage, color = Color.White)
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