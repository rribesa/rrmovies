package br.com.rrs.rrmovies.genre.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rrs.rrmovies.genre.repository.GenreRepository

@Suppress("UNCHECKED_CAST")
class GenreViewModelFactory(private val repository: GenreRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GenreViewModel(this.repository) as T
    }
}