package br.com.rrs.rrmovies.genre.viewmodel.viewState

import br.com.rrs.rrmovies.genre.model.Genres

sealed class GenreViewState {
    data class GenreProgressBarVisible(val visibility: Int) : GenreViewState()
    data class GenreListLoaded(val genres: Genres) : GenreViewState()
    data class GenreError(val error: String) : GenreViewState()
    data class GenreProgressBarGone(val visibility: Int) : GenreViewState()
}