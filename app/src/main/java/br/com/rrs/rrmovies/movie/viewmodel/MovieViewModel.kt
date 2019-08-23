package br.com.rrs.rrmovies.movie.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rrs.rrmovies.movie.model.Result
import br.com.rrs.rrmovies.movie.repository.MovieRepository
import br.com.rrs.rrmovies.movie.viewmodel.viewstate.MovieViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val state: MutableLiveData<MovieViewState> = MutableLiveData()
    val viewState: LiveData<MovieViewState> = state

    fun init() {
        state.value = MovieViewState.MovieProgressBarVisible(View.VISIBLE)
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                try {
                    state.postValue((MovieViewState.MovieListLoaded(repository.getMovieListAsync().await())))
                } catch (error: Throwable) {
                    error.printStackTrace()
                    state.postValue(MovieViewState.MovieError(error.message.toString()))
                } finally {
                    state.postValue(MovieViewState.MovieProgressBarGone(View.GONE))
                }
            }
        }
    }

    fun clickMovie(movie: Result) {
        state.postValue(MovieViewState.MovieClicked(movie))

    }
}