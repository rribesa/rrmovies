package br.com.rrs.rrmovies.movie

import br.com.rrs.rrmovies.genre.model.Genre
import br.com.rrs.rrmovies.movie.model.Movie
import br.com.rrs.rrmovies.movie.repository.MovieRepository

class MovieUseCase(private val repository: MovieRepository) {

    suspend fun getMovies(): List<Movie> {
        val genre = repository.getGenres().genres
        val result = repository.getMovieList().results
        val movies = ArrayList<Movie>()
        result.forEach { resultMovie ->
            movies.add(
                Movie(
                    resultMovie.backdrop_path,
                    resultMovie.id,
                    resultMovie.overview,
                    resultMovie.poster_path,
                    resultMovie.release_date,
                    resultMovie.title,
                    getGenresName(resultMovie.genre_ids, genre)
                )
            )
        }
        return movies
    }

    private fun getGenresName(ids: List<Int>, genre: List<Genre>): List<Genre> {
        val movieGenre = ArrayList<Genre>()
        ids.forEach { genreId ->
            movieGenre.addAll(genre.filter { genre -> genreId == genre.id })
        }
        return movieGenre
    }
}
