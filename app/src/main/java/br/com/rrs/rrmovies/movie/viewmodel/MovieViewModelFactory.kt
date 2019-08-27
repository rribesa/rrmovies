package br.com.rrs.rrmovies.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rrs.rrmovies.movie.MovieUseCase

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(private val useCase: MovieUseCase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(this.useCase) as T
    }
}