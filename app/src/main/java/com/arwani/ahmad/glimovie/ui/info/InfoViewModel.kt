package com.arwani.ahmad.glimovie.ui.info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arwani.ahmad.glimovie.data.local.entity.GenreMovies
import com.arwani.ahmad.glimovie.data.local.entity.Movie
import com.arwani.ahmad.glimovie.data.network.response.Genre
import com.arwani.ahmad.glimovie.data.network.response.Result
import com.arwani.ahmad.glimovie.data.network.response.Video
import com.arwani.ahmad.glimovie.data.repository.MoviesRepository
import com.arwani.ahmad.glimovie.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Movie>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Movie>>
        get() = _uiState

    private val _videoUiState: MutableStateFlow<UiState<List<Video>>> =
        MutableStateFlow(UiState.Loading)
    val videoUiState: StateFlow<UiState<List<Video>>> get() = _videoUiState

    private val _reviewUiState: MutableStateFlow<UiState<List<Result>>> =
        MutableStateFlow(UiState.Loading)
    val reviewMovies: StateFlow<UiState<List<Result>>> get() = _reviewUiState

    private val _genres = MutableStateFlow(emptyList<Int>())

    fun getMovieById(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            moviesRepository.getMovieId(id)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { movie ->
                    _genres.value = movie.genres
                    _uiState.value = UiState.Success(movie)
                }
        }
    }

    fun getMovieVideo(id: Int) {
        viewModelScope.launch {
            _videoUiState.value = UiState.Loading
            moviesRepository.getVideoMovies(id)
                .catch {
                    _videoUiState.value = UiState.Error(it.message.toString())
                }
                .collect { video ->
                    _videoUiState.value = UiState.Success(video)
                }
        }
    }

    fun getReviewMovies(movieInt: Int) {
        viewModelScope.launch {
            _reviewUiState.value = UiState.Loading
            moviesRepository.getReviewMovies(movieInt)
                .catch { _reviewUiState.value = UiState.Error(it.message.toString()) }
                .collect { _reviewUiState.value = UiState.Success(it) }
        }
    }

    fun getAllGenres(): Flow<List<GenreMovies>> = moviesRepository.getGenres()
}