package com.arwani.ahmad.glimovie.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arwani.ahmad.glimovie.ui.theme.Blue100
import com.arwani.ahmad.glimovie.ui.theme.Shapes

@Composable
fun SearchBar(modifier: Modifier = Modifier, navigateSearch: () -> Unit) {
    TextField(
        value = "",
        onValueChange = { },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primary,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text("Search title ...")
        },
        modifier = modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .heightIn(max = 50.dp)
            .border(BorderStroke(width = 1.dp, color = Color.White.copy(0.2f)), shape = RoundedCornerShape(16.dp))
            .clickable {
                navigateSearch()
            },
        enabled = false,
    )
}