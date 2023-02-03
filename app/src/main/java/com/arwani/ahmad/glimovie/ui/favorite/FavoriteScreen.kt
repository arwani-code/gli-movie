package com.arwani.ahmad.glimovie.ui.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arwani.ahmad.glimovie.ui.components.TopMovieBar
import com.arwani.ahmad.glimovie.ui.home.MovieItem
import com.arwani.ahmad.glimovie.ui.theme.Green100

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val favorites by favoriteViewModel.favorites.collectAsState(initial = emptyList())
    Scaffold(topBar = {
        TopMovieBar(canNavigate = false, title = "Favorite")
    }, backgroundColor = MaterialTheme.colors.primary) { innerPadding ->
        if (favorites.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier.padding(innerPadding),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(favorites, key = { it.id }) { movie ->
                    MovieItem(navigateToDetail = { navigateToDetail(it) }, movie = movie)
                }
            }
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "No movies have been saved.", fontSize = 16.sp, color = Green100)
            }
        }
    }
}