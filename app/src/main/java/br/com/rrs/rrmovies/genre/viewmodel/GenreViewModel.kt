package br.com.rrs.rrmovies.genre.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rrs.rrmovies.genre.model.Genre
import br.com.rrs.rrmovies.genre.repository.GenreRepository
import br.com.rrs.rrmovies.genre.viewmodel.viewState.GenreViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GenreViewModel(private val repository: GenreRepository) : ViewModel() {
    private val state: MutableLiveData<GenreViewState> = MutableLiveData()
    val viewState: LiveData<GenreViewState> = state

    fun bind() {
        state.value = GenreViewState.GenreProgressBarVisibility(View.VISIBLE)
        runBlocking {
            state.value = GenreViewState.GenreListLoaded(repository.getGenreList())
        }
        GlobalScope.launch(Dispatchers.Main) {
            try {
                state.postValue(GenreViewState.GenreListLoaded(repository.getGenreList()))
            } catch (error: Exception) {
                error.printStackTrace()
                state.postValue(GenreViewState.GenreError(error.message.toString()))
            } finally {
                state.postValue(GenreViewState.GenreProgressBarVisibility(View.GONE))
            }
        }
    }

    fun clickGenre(genre: Genre) {
        state.value = GenreViewState.GenreClick(genre)
    }
}