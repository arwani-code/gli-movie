package com.arwani.ahmad.glimovie.ui.info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arwani.ahmad.glimovie.R
import com.arwani.ahmad.glimovie.data.network.response.Result
import com.arwani.ahmad.glimovie.ui.theme.Blue900

@Composable
fun ReviewsScreen(
    modifier: Modifier = Modifier,
    review: Result
) {
    Card(
        elevation = 3.dp,
        modifier = modifier,
        backgroundColor = Blue900
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 22.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(8.dp))

            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500${review.author_details.avatar_path}")
                        .crossfade(true)
                        .build(),
                    error = painterResource(id = R.drawable.user),
                    contentDescription = review.author,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.clip(CircleShape),
                    placeholder = painterResource(id = R.drawable.user)
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = review.author,
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )
                Text(
                    text = review.content,
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }
        }
    }
}