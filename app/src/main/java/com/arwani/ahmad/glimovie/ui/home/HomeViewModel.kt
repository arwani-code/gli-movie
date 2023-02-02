package com.arwani.ahmad.glimovie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arwani.ahmad.glimovie.data.local.entity.Movie
import com.arwani.ahmad.glimovie.data.local.room.MoviesDatabase
import com.arwani.ahmad.glimovie.data.network.retrofit.MoviesApiService
import com.arwani.ahmad.glimovie.data.repository.MoviesRemoteMediator
import com.arwani.ahmad.glimovie.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_SIZE = 20

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesApiService: MoviesApiService,
    private val moviesDatabase: MoviesDatabase,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    fun getPopularMovies(playing: String): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                moviesDatabase.getMoviesDao().getMovies()
            },
            remoteMediator = MoviesRemoteMediator(
                moviesApiService,
                moviesDatabase,
                playing
            ),
        ).flow

    fun fetchGenres(){
        viewModelScope.launch {
            moviesRepository.getGenresMovies()
                .flowOn(Dispatchers.IO)
                .catch {}
                .collect {}
        }
    }
}