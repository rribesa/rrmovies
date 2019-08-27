package br.com.rrs.rrmovies.movie.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rrs.rrmovies.movie.MovieUseCase
import br.com.rrs.rrmovies.movie.model.Movie
import br.com.rrs.rrmovies.movie.viewmodel.viewstate.MovieViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private val useCase: MovieUseCase) : ViewModel() {
    private val state: MutableLiveData<MovieViewState> = MutableLiveData()
    val viewState: LiveData<MovieViewState> = state

    fun init() {
        state.value = MovieViewState.MovieProgressBarVisible(View.VISIBLE)
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                try {
                    state.postValue((MovieViewState.MovieListLoaded(useCase.getMovies())))
                } catch (error: Exception) {
                    error.printStackTrace()
                    state.postValue(MovieViewState.MovieError(error.message.toString()))
                } finally {
                    state.postValue(MovieViewState.MovieProgressBarGone(View.GONE))
                }
            }
        }
    }

    fun clickMovie(movie: Movie) {
        state.postValue(MovieViewState.MovieClicked(movie))
    }
}