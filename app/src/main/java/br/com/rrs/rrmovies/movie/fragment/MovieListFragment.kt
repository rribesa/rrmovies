package br.com.rrs.rrmovies.movie.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rrs.rrmovies.R
import br.com.rrs.rrmovies.movie.adapter.MoviesAdapter
import br.com.rrs.rrmovies.movie.model.Movie
import br.com.rrs.rrmovies.movie.viewmodel.MovieViewModel
import br.com.rrs.rrmovies.movie.viewmodel.viewstate.MovieViewState
import com.airbnb.lottie.LottieAnimationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {

    val movieViewModel: MovieViewModel by viewModel()
    lateinit var animation: LottieAnimationView
    lateinit var movieRecycleList: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animation = view.findViewById(R.id.movie_loading_animation)
        movieRecycleList = view.findViewById(R.id.movie_recycle_list)
        initViewModel()
    }

    private fun initViewModel() {
        movieViewModel.viewState.observe(viewLifecycleOwner, Observer { movieState ->
            movieState?.let {
                when (it) {
                    is MovieViewState.MovieProgressBarVisible -> animation.visibility = it.visibility
                    is MovieViewState.MovieListLoaded -> fillList(it.movies)
                    is MovieViewState.MovieError -> navigateError(it.error)
                    is MovieViewState.MovieProgressBarGone -> animation.visibility = it.visibility
                    is MovieViewState.MovieClicked -> navigateMovie(it.movie)
                }
            }
        })
        movieViewModel.init()
    }

    private fun fillList(movies: List<Movie>) {
        movieRecycleList.layoutManager = LinearLayoutManager(this.context)
        movieRecycleList.adapter = MoviesAdapter(movies, movieViewModel)
        movieRecycleList.visibility = View.VISIBLE
    }

    private fun navigateError(error: String) {
        println("error$error")
        findNavController().navigate(R.id.action_movieListFragment_to_movieErrorFragment)
    }

    private fun navigateMovie(movie: Movie) {
        val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }
}
