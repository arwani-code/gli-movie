package com.arwani.ahmad.glimovie.ui.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.arwani.ahmad.glimovie.data.local.entity.Movie
import com.arwani.ahmad.glimovie.ui.theme.Green100

@Composable
fun MovieInfo(
    modifier: Modifier = Modifier,
    title: String,
    genre: String,
    voteAverage: String,
    voteCount: String,
    posterPath: String,
    releaseDate: String,
    popularity: String
) {
    Row(
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(270.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500$posterPath")
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(250.dp)
                .width(160.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
        )
        Column(
            modifier = modifier
                .padding(start = 8.dp)
                .height(150.dp), verticalArrangement = Arrangement.SpaceEvenly
        ) {
            NameTitle(title = "Title", name = title)
            NameTitle(title = "Release Date", name = releaseDate)
            NameTitle(title = "Genre", name = genre)
            NameTitle(title = "Vote Average", name = voteAverage)
            NameTitle(title = "Popularity", name = popularity)
            NameTitle(title = "Vote Count", name = voteCount)
        }
    }
//    Divider(startIndent = 50.dp, color = Green100, modifier = modifier.padding(end = 50.dp))
}

@Composable
fun NameTitle(
    modifier: Modifier = Modifier,
    title: String,
    name: String
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append(text = "$title : ")
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(0.9f)
                )
            ) {
                append(text = " $name ")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MovieInfoPreview() {
    MovieInfo(
        modifier = Modifier.background(MaterialTheme.colors.primary),
        title = "Avatar: The Way of Water",
        genre = "Rock, Rock, Rock, Rock, Rock, Rock, Rock, Rock",
        voteAverage = "7,7",
        voteCount = "38383",
        posterPath = "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
        releaseDate = "2022-20-3",
        popularity = "2334.4"
    )
}