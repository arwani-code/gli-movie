package com.arwani.ahmad.glimovie

import com.arwani.ahmad.glimovie.data.FakeApiService
import com.arwani.ahmad.glimovie.data.FakeMoviesDao
import com.arwani.ahmad.glimovie.data.repository.MoviesRepository
import com.arwani.ahmad.glimovie.ui.info.InfoViewModel
import com.google.common.truth.ExpectFailure.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryTest {

    @get: Rule
    val dispatcherRule = ViewModelRule()

    private lateinit var infoViewModel: InfoViewModel
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var moviesDao: FakeMoviesDao
    private lateinit var movieApiService: FakeApiService

    @Before
    fun setUp(){
        moviesDao = FakeMoviesDao()
        movieApiService = FakeApiService()
        moviesRepository = MoviesRepository(moviesDao = moviesDao, moviesApiService = movieApiService)
        infoViewModel = InfoViewModel(moviesRepository)
    }

}