package br.com.rrs.rrmovies.genre.viewmodel.viewState

import br.com.rrs.rrmovies.genre.model.Genre
import br.com.rrs.rrmovies.genre.model.Genres

sealed class GenreViewState {
    data class GenreProgressBarVisibility(val visibility: Int) : GenreViewState()
    data class GenreListLoaded(val genres: Genres) : GenreViewState()
    data class GenreError(val error: String) : GenreViewState()
    data class GenreClick(val genre: Genre) : GenreViewState()
}