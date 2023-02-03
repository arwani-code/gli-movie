package com.arwani.ahmad.glimovie.ui.profile

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arwani.ahmad.glimovie.R
import com.arwani.ahmad.glimovie.ui.components.TopMovieBar
import com.arwani.ahmad.glimovie.ui.theme.Green100

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(topBar = {
        TopMovieBar(canNavigate = false, title = "Profile")
    }, backgroundColor = MaterialTheme.colors.primary) {
        Column(
            modifier = modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .padding(top = 80.dp, bottom = 8.dp)
                        .clip(CircleShape)
                        .size(200.dp),
                    painter = painterResource(id = R.drawable.photo_profile),
                    contentDescription = stringResource(R.string.foto_profile),
                    contentScale = ContentScale.Crop
                )
                Text(text = "Android Developer", fontWeight = FontWeight.Bold, color = Green100)
                Divider(
                    modifier = modifier.padding(top = 20.dp, end = 100.dp, bottom = 50.dp),
                    color = Green100,
                    startIndent = 100.dp
                )
                NameSection(title = "Name", value = "Ahmad Arwani")
                NameSection(title = "Age", value = "23")
                NameSection(title = "Email", value = "arwaniahmad659@gmail.com")
            }
        }
    }
}

@Composable
fun NameSection(modifier: Modifier = Modifier, title: String, value: String) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 17.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append(text = "$title : ")
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(0.9f)
                )
            ) {
                append(text = " $value ")
            }
        }
    )
    Divider(
        modifier = modifier.padding(top = 20.dp, end = 50.dp, bottom = 12.dp),
        color = Green100,
        startIndent = 50.dp
    )
}