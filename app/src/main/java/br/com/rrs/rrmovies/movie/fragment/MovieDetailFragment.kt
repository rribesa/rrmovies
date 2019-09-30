package br.com.rrs.rrmovies.movie.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.rrs.rrmovies.R
import br.com.rrs.rrmovies.movie.model.Movie
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {
    private var movie: Movie? = null
    private lateinit var imageView: ImageView
    private lateinit var titleMovie: TextView
    private lateinit var dateMovie: TextView
    private lateinit var sinopseMovie: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = view.findViewById(R.id.movie_detail_image)
        titleMovie = view.findViewById(R.id.movie_detail_title)
        dateMovie = view.findViewById(R.id.movie_detail_date)
        sinopseMovie = view.findViewById(R.id.movie_detail_sinopse)
        updateUI()
    }

    private fun updateUI() {
        val safeArgs: MovieDetailFragmentArgs by navArgs()
        movie = safeArgs.movie
        movie?.let {
            titleMovie.text = it.title
            dateMovie.text = it.release_date
            sinopseMovie.text = it.overview
            Picasso.with(this.context)
                .load(br.com.rrs.rrmovies.movie.adapter.BASE_URL + it.backdrop_path)
                .resize(500,500)
                .centerCrop()
                .into(imageView)
        }
    }
}