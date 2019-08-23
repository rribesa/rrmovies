package br.com.rrs.rrmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.rrs.rrmovies.movie.model.Result
import com.squareup.picasso.Picasso

private const val MOVIE = "movie"
class MovieDetailFragment : Fragment() {
    private var movie: Result? = null
    private lateinit var imageView: ImageView
    private lateinit var titleMovie: TextView
    private lateinit var dateMovie: TextView
    private lateinit var sinopseMovie: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val safeArgs: MovieDetailFragmentArgs by navArgs()
        movie = safeArgs.movie
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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
        movie?.let {
            titleMovie.text = it.title
            dateMovie.text = it.release_date
            sinopseMovie.text = it.overview
            Picasso.with(this.context)
                .load(br.com.rrs.rrmovies.movie.ui.adapter.BASE_URL + it.poster_path)
                .into(imageView)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(movie: Result) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE, movie)
                }
            }
    }

}
