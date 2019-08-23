package br.com.rrs.rrmovies.movie.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rrs.rrmovies.R
import br.com.rrs.rrmovies.Service
import br.com.rrs.rrmovies.WebClient
import br.com.rrs.rrmovies.movie.model.Movies
import br.com.rrs.rrmovies.movie.model.Result
import br.com.rrs.rrmovies.movie.repository.MovieRepository
import br.com.rrs.rrmovies.movie.ui.adapter.MoviesAdapter
import br.com.rrs.rrmovies.movie.viewmodel.MovieViewModel
import br.com.rrs.rrmovies.movie.viewmodel.MovieViewModelFactory
import br.com.rrs.rrmovies.movie.viewmodel.viewstate.MovieViewState
import com.airbnb.lottie.LottieAnimationView


class MovieListFragment : Fragment() {

    lateinit var repository: MovieRepository
    lateinit var webClient: Service
    lateinit var factory: MovieViewModelFactory
    lateinit var viewModel: MovieViewModel
    lateinit var animation: LottieAnimationView
    lateinit var movieRecycleList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webClient = WebClient().service()
        repository = MovieRepository(webClient)
        factory = MovieViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(
            MovieViewModel::class.java
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animation = view.findViewById(R.id.movie_loading_animation)
        movieRecycleList = view.findViewById(R.id.movie_recycle_list)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is MovieViewState.MovieProgressBarVisible -> animation.visibility = it.visibility
                is MovieViewState.MovieListLoaded -> fillList(it.movies)
                is MovieViewState.MovieError -> navigateError(it.error)
                is MovieViewState.MovieProgressBarGone -> animation.visibility = it.visibility
            }
        })
        viewModel.init()
    }

    private fun fillList(movies: Movies) {
        movieRecycleList.layoutManager = GridLayoutManager(this.context, 2)
        movieRecycleList.adapter = MoviesAdapter(movies)
        movieRecycleList.visibility = View.VISIBLE
    }

    private fun navigateError(error: String) {
        Log.d("passou", error)
        findNavController().navigate(R.id.action_movieListFragment_to_movieErrorFragment)
    }

    private fun navigateMovie(movie: Result) {
        Log.d("passou", movie.toString())

        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }
}
