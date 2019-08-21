package br.com.rrs.rrmovies.genre.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rrs.rrmovies.genre.repository.GenreRepository
import br.com.rrs.rrmovies.genre.viewmodel.viewState.GenreViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenreViewModel(private val repository: GenreRepository) : ViewModel() {
    private val state: MutableLiveData<GenreViewState> = MutableLiveData()
    val viewState: LiveData<GenreViewState> = state

    fun init() {
        GlobalScope.launch(Dispatchers.Main) {
            state.value = GenreViewState.GenreProgressBarVisible(View.VISIBLE)
            withContext(Dispatchers.IO) {
                try {
                    state.postValue((GenreViewState.GenreListLoaded(repository.getGenreList())))
                } catch (error: Exception) {
                    error.printStackTrace()
                    state.postValue(GenreViewState.GenreError(error.message.toString()))
                } finally {
                    state.postValue(GenreViewState.GenreProgressBarGone(View.GONE))
                }
            }
        }
    }
}