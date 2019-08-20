package br.com.rrs.rrmovies

import br.com.rrs.rrmovies.genre.model.Genres
import br.com.rrs.rrmovies.movie.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

const val APIKEY = "api_key"
const val APIKEY_VALUE = "1f54bd990f1cdfb230adb312546d765d"
const val LANGUAGE = "language"
const val LANGUAGE_VALUE = "pt-BR"
const val PAGE = "page"
const val PAGE_VALUE = 1

interface Service {
    @GET("/list?")
    suspend fun getGenres(
        @Query(APIKEY) apiKey: String = APIKEY_VALUE,
        @Query(LANGUAGE) language: String = LANGUAGE_VALUE
    ): Genres

    @GET("/upcoming?")
    suspend fun getMovies(
        @Query(APIKEY) apiKey: String = APIKEY_VALUE,
        @Query(LANGUAGE) language: String = LANGUAGE_VALUE,
        @Query(PAGE) page: String = PAGE_VALUE.toString()
    ): Movies

}