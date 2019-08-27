package br.com.rrs.rrmovies.movie

import br.com.rrs.rrmovies.genre.model.Genre
import br.com.rrs.rrmovies.movie.repository.MovieRepository
import br.com.rrs.rrmovies.movie.response.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieUseCaseTest {

    @MockK
    lateinit var repository: MovieRepository

    lateinit var useCase: MovieUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        coEvery { repository.getGenres().genres } returns listOf(
            Genre(1, "terror"),
            Genre(2, "comedia"),
            Genre(3, "aventura")
        )
        val result = getResult()
        coEvery { repository.getMovieList().results } returns (listOf(result))
        useCase = MovieUseCase(repository)
    }

    @Test
    fun getMoviesTest() {
        val result = runBlocking {
            useCase.getMovies()
        }
        Assert.assertEquals("terror", result[0].genres[0].name)
    }

    private fun getResult() = Result(
        false,
        " ",
        listOf(1, 2),
        1,
        "portuges",
        "teste filme",
        "este filme serve apenas para teste",
        5.00,
        "",
        "25/04/2010",
        "teste filme",
        true,
        5.00,
        5
    )
}