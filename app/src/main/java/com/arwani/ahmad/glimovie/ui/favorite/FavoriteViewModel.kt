package com.arwani.ahmad.glimovie.ui.favorite

import androidx.lifecycle.ViewModel
import com.arwani.ahmad.glimovie.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(moviesRepository: MoviesRepository) :
    ViewModel() {
    val favorites = moviesRepository.getFavoriteMovies()
}