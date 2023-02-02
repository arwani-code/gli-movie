package com.arwani.ahmad.glimovie.ui.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.arwani.ahmad.glimovie.data.network.response.Result

@Composable
fun ReviewsScreen(
    modifier: Modifier = Modifier,
    review: Result
) {
    val painter = rememberAsyncImagePainter(
        model = "https://image.tmdb.org/t/p/w154" + review.author_details.avatar_path,
    )
    var isImageLoading by remember { mutableStateOf(false) }
    isImageLoading = when (painter.state) {
        is AsyncImagePainter.State.Loading -> true
        else -> false
    }

    Row(
        modifier = Modifier.clickable { },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var isImageLoading by remember { mutableStateOf(false) }
        val painter = rememberAsyncImagePainter(
            model = "https://image.tmdb.org/t/p/w500" + review.author_details.avatar_path,
        )
        isImageLoading = when (painter.state) {
            is AsyncImagePainter.State.Loading -> true
            else -> false
        }
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 3.dp)
                    .height(115.dp)
                    .width(77.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painter,
                contentDescription = "Poster Image",
                contentScale = ContentScale.FillBounds,
            )

            if (isImageLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 3.dp),
                    color = MaterialTheme.colors.primary,
                )
            }
        }
        Column {
            Text(
                modifier = Modifier
                    .padding(vertical = 18.dp, horizontal = 8.dp),
                text = review.author_details.username,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = review.content,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
    Divider()
}