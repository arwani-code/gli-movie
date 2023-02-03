package com.arwani.ahmad.glimovie.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.arwani.ahmad.glimovie.data.local.entity.Movie
import com.arwani.ahmad.glimovie.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    var userInput by mutableStateOf("")

    fun inputValue(search: String) {
        userInput = search
    }

    fun getMovies(): Flow<List<Movie>> = moviesRepository.searchPosts("%$userInput%")

    fun setEmptyQuery() {
        userInput = ""
    }

}