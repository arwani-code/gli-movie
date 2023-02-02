package com.arwani.ahmad.glimovie.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arwani.ahmad.glimovie.ui.components.TopMovieBar

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(topBar = {
        TopMovieBar(canNavigate = false, title = "Profile")
    }, backgroundColor = MaterialTheme.colors.primary) {
        Column(modifier = modifier.padding(it)) {

        }
    }
}