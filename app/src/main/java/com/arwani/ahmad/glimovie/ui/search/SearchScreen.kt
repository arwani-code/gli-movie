package com.arwani.ahmad.glimovie.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arwani.ahmad.glimovie.ui.home.MovieItem

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel(),
    navigate: () -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    val getMovies by searchViewModel.getMovies().collectAsState(initial = emptyList())
    Scaffold(
        modifier = modifier.background(MaterialTheme.colors.primary),
        topBar = {
            Column {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.primary)
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { navigate() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "arrow_back",
                                tint = Color.White
                            )
                        }
                        OutlinedTextField(
                            value = searchViewModel.userInput,
                            onValueChange = { searchViewModel.inputValue(it) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            modifier = modifier
                                .width(300.dp)
                                .heightIn(min = 25.dp)
                                .focusRequester(focusRequester),
                            singleLine = true,
                            shape = RoundedCornerShape(percent = 30),
                            placeholder = {
                                Text(
                                    color = Color.White.copy(0.4f),
                                    text = "Search Movie ...",
                                    fontSize = 16.sp,
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                textColor = Color.White,
                                cursorColor = Color.White,
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = Color.White.copy(0.7f)
                                )
                            },
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus() }
                            ),
                            trailingIcon = {
                                if (searchViewModel.userInput.isNotEmpty()) {
                                    IconButton(onClick = { searchViewModel.setEmptyQuery() }) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = "Clear",
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
                Divider(color = Color.White.copy(0.2f))
            }
        },
        backgroundColor = MaterialTheme.colors.primary
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.padding(innerPadding),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(getMovies, key = { it.id }) { movie ->
                MovieItem(navigateToDetail = { navigateToDetail(it) }, movie = movie)
            }
        }
    }
}