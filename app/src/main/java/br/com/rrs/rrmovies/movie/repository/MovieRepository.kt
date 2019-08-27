package br.com.rrs.rrmovies.movie.repository

import br.com.rrs.rrmovies.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val service: Service) {

    suspend fun getMovieList() = withContext(Dispatchers.IO) { service.getMovies() }
    suspend fun getGenres() = withContext(Dispatchers.IO) { service.getGenres() }
}