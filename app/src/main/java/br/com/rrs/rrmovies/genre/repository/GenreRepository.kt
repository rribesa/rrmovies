package br.com.rrs.rrmovies.genre.repository

import br.com.rrs.rrmovies.Service
import br.com.rrs.rrmovies.genre.model.Genres
import kotlinx.coroutines.*

class GenreRepository(private val service: Service) {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)
    fun getGenreListAsync(): Deferred<Genres> = scope.async { service.getGenres() }
}