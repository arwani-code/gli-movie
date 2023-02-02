package com.arwani.ahmad.glimovie.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.arwani.ahmad.glimovie.data.repository.MoviesRepository
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

     var userInput by mutableStateOf("")

    fun inputValue(search: String){
        userInput = search
    }

    fun setEmptyQuery(){
        userInput = ""
    }

}