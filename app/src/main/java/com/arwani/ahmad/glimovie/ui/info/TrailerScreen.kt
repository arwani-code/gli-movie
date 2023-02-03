package com.arwani.ahmad.glimovie.ui.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arwani.ahmad.glimovie.R
import com.arwani.ahmad.glimovie.data.network.response.Video
import com.arwani.ahmad.glimovie.ui.theme.Red200

@Composable
fun TrailerScreen(
    modifier: Modifier = Modifier,
    videos: Video,
    navigateClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(120.dp)
            .height(160.dp)
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .background(color = Color.Black)
            .clickable { navigateClick() },
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(color = Red200),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = modifier.size(120.dp),
                painter = painterResource(id = R.drawable.youtubetrailer),
                contentDescription = "Trailer"
            )
        }
        Text(
            text = videos.name,
            color = Color.White,
            modifier = modifier.padding(top = 10.dp, start = 8.dp),
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}