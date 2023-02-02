package com.arwani.ahmad.glimovie.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.arwani.ahmad.glimovie.data.local.entity.Movie

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    movie: Movie
) {
    Column(
        modifier = modifier
            .width(180.dp)
            .height(230.dp)
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .background(color = Color.Black)
            .clickable { navigateToDetail(movie.id) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(190.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (movie.posterPath != null) {
                var isImageLoading by remember { mutableStateOf(false) }
                val painter = rememberAsyncImagePainter(
                    model = "https://image.tmdb.org/t/p/w154" + movie.posterPath,
                )
                isImageLoading = when (painter.state) {
                    is AsyncImagePainter.State.Loading -> true
                    else -> false
                }
                Image(
                    modifier = modifier.size(100.dp),
                    painter = painter,
                    contentDescription = movie.title
                )
                if (isImageLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 3.dp),
                        color = MaterialTheme.colors.primary,
                    )
                }
            }
        }
        Text(
            text = movie.title,
            color = Color.White,
            modifier = modifier.padding(top = 10.dp, start = 8.dp)
        )
    }
}