package br.com.rrs.rrmovies.genre.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rrs.rrmovies.R
import br.com.rrs.rrmovies.Service
import br.com.rrs.rrmovies.WebClient
import br.com.rrs.rrmovies.genre.model.Genre
import br.com.rrs.rrmovies.genre.model.Genres
import br.com.rrs.rrmovies.genre.repository.GenreRepository
import br.com.rrs.rrmovies.genre.ui.adapter.GenresAdapter
import br.com.rrs.rrmovies.genre.viewmodel.GenreViewModel
import br.com.rrs.rrmovies.genre.viewmodel.GenreViewModelFactory
import br.com.rrs.rrmovies.genre.viewmodel.viewstate.GenreViewState
import com.airbnb.lottie.LottieAnimationView

class GenreListFragment : Fragment() {
    lateinit var repository: GenreRepository
    lateinit var webClient: Service
    lateinit var factory: GenreViewModelFactory
    lateinit var viewModel: GenreViewModel
    lateinit var animation: LottieAnimationView
    lateinit var genreRecycleList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webClient = WebClient().service()
        repository = GenreRepository(webClient)
        factory = GenreViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(
            GenreViewModel::class.java
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animation = view.findViewById(R.id.genre_loading_animation)
        genreRecycleList = view.findViewById(R.id.genre_recycle_list)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                is GenreViewState.GenreProgressBarVisible -> animation.visibility = it.visibility
                is GenreViewState.GenreListLoaded -> fillList(it.genres)
                is GenreViewState.GenreError -> navigateError(it.error)
                is GenreViewState.GenreProgressBarGone -> animation.visibility = it.visibility
            }
        })
        viewModel.init()
    }

    private fun fillList(genres: Genres) {
        Log.d("SUCESSO_GENRELIST", "###### sucesso #####" + genres.toString())
        genreRecycleList.layoutManager = LinearLayoutManager(this.context)
        genreRecycleList.adapter = GenresAdapter(genres)
        genreRecycleList.visibility = View.VISIBLE
        genreRecycleList.addItemDecoration(
            DividerItemDecoration(
                genreRecycleList.context,
                DividerItemDecoration.VERTICAL
            )
        )

    }

    private fun navigateGenre(genre: Genre) {
        Log.d("SUCESSO_GENRELIST", "###### sucesso #####" + genre.toString())

    }

    private fun navigateError(error: String) {
        Log.d("ERROR_GENRELIST", "###### error #####" + error)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GenreListFragment()
    }
}