package br.com.rrs.rrmovies.genre.repository

import br.com.rrs.rrmovies.Service
import br.com.rrs.rrmovies.genre.model.Genres

class GenreRepository(private val service: Service) {
    suspend fun getGenreList(): Genres {
        service.getGenres()
        throw NullPointerException()
    }
}