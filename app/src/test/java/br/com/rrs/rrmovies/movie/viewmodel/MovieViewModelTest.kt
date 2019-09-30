package br.com.rrs.rrmovies.movie.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.rrs.rrmovies.genre.model.Genre
import br.com.rrs.rrmovies.movie.CoroutineTestRule
import br.com.rrs.rrmovies.movie.MovieUseCase
import br.com.rrs.rrmovies.movie.model.Movie
import br.com.rrs.rrmovies.movie.viewmodel.viewstate.MovieViewState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    val scope = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var useCase: MovieUseCase

    lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        coEvery { useCase.getMovies() } returns listOf(
            Movie(
                null, 1, "teste", "imgteste", "240319", "titulo do teste", listOf(
                    Genre(1, "terror")
                )
            )
        )
        viewModel = MovieViewModel(useCase)
    }

    @Test
    fun getMovieStateSuccess() {
        scope.run {  viewModel.init()}
        Assert.assertEquals(viewModel.viewState.value, MovieViewState.MovieProgressBarVisible(View.VISIBLE))
    }

}