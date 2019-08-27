package br.com.rrs.rrmovies.movie.repository

import br.com.rrs.rrmovies.Service
import br.com.rrs.rrmovies.genre.response.Genres
import br.com.rrs.rrmovies.movie.response.Movies
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {
    private lateinit var repository: MovieRepository

    @MockK
    lateinit var service: Service

    @MockK
    lateinit var movies: Movies

    @MockK
    lateinit var genres: Genres

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        coEvery { service.getMovies() } returns movies
        coEvery { service.getGenres() } returns genres
        every { movies.page } returns 0
        every { genres.genres[0].name } returns "teste"
        repository = MovieRepository(service)
    }

    @Test
    fun getMovieListTest() {
        val result = runBlocking {
            repository.getMovieList()
        }
        Assert.assertEquals(0, result.page)
    }

    @Test
    fun getGenreListTest() {
        val result = runBlocking {
            repository.getGenres()
        }
        Assert.assertEquals("teste", result.genres[0].name)
    }
}