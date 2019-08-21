package br.com.rrs.rrmovies.genre.repository

import br.com.rrs.rrmovies.Service

class GenreRepository(private val service: Service) {
    suspend fun getGenreList() = service.getGenres()
}