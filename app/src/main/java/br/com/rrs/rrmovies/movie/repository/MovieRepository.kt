package br.com.rrs.rrmovies.movie.repository

import br.com.rrs.rrmovies.Service
import br.com.rrs.rrmovies.genre.model.Genres
import br.com.rrs.rrmovies.movie.model.Movies
import kotlinx.coroutines.*

class MovieRepository(val service: Service) {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)
    fun getMovieListAsync(): Deferred<Movies> = scope.async { service.getMovies() }
    fun getGenresAsync(): Deferred<Genres> = scope.async { service.getGenres() }
}