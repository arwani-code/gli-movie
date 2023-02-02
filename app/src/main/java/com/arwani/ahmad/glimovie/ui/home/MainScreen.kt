package com.arwani.ahmad.glimovie.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.arwani.ahmad.glimovie.R
import com.arwani.ahmad.glimovie.ui.theme.Green100

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.fetchGenres()
    val movies = viewModel.getPopularMovies("now_playing").collectAsLazyPagingItems()


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        items(movies.itemCount) { index ->
            movies[index]?.let { movie ->
                MovieItem(movie = movie, navigateToDetail = { navigateToDetail(it) })
            }
        }


        val loadState = movies.loadState.mediator
        item(span = { GridItemSpan(2) }) {
            if (loadState?.refresh == LoadState.Loading) {
                Column(
                    modifier = modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = modifier
                            .padding(8.dp),
                        text = stringResource(R.string.refresh_loading)
                    )

                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
            }

            if (loadState?.append == LoadState.Loading) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
            }

            if (loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error) {
                val isPaginatingError =
                    (loadState.append is LoadState.Error) || movies.itemCount > 1
                val error = if (loadState.append is LoadState.Error)
                    (loadState.append as LoadState.Error).error
                else
                    (loadState.refresh as LoadState.Error).error

                val modifier = if (isPaginatingError) {
                    modifier.padding(8.dp)
                } else {
                    modifier.fillMaxSize()
                }
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (!isPaginatingError) {
                        Icon(
                            modifier = modifier
                                .size(64.dp),
                            imageVector = Icons.Rounded.Warning, contentDescription = null
                        )
                    }

                    Text(
                        modifier = modifier
                            .padding(8.dp),
                        text = error.message ?: error.toString(),
                        textAlign = TextAlign.Center,
                    )

                    Button(
                        onClick = {
                            movies.refresh()
                        },
                        content = {
                            Text(text = "Refresh")
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Green100,
                            contentColor = MaterialTheme.colors.primary,
                        )
                    )
                }
            }
        }
    }
}
