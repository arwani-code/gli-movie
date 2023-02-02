package com.arwani.ahmad.glimovie.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel(),
    navigate: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

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
                                    text = "Search title ...",
                                    fontSize = 16.sp,
                                    color = Color.White.copy(0.4f)
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
    ) {
        Column(modifier = modifier
            .padding(it)) {

        }
    }
}