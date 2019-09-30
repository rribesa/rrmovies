package br.com.rrs.rrmovies.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rrs.rrmovies.R
import br.com.rrs.rrmovies.genre.adapter.GenresAdapter
import br.com.rrs.rrmovies.movie.model.Movie
import br.com.rrs.rrmovies.movie.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso

const val BASE_URL = "https://image.tmdb.org/t/p/w185/"

class MoviesAdapter(
    private val movies: List<Movie>,
    private val viewModel: MovieViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bindMovie(movies[position], viewModel)
    }
}

class MovieViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bindMovie(movie: Movie, viewModel: MovieViewModel) {
        val imageMovie: ImageView = itemView.findViewById(R.id.movie_image)
        Picasso.with(itemView.context)
            .load(BASE_URL + movie.poster_path)
            .resize(200,200)
            .centerCrop()
            .into(imageMovie)
        val titleMovie: TextView = itemView.findViewById(R.id.movie_name)
        titleMovie.text = movie.title
        val dateMovie: TextView = itemView.findViewById(R.id.movie_date)
        dateMovie.text = movie.release_date
        itemView.setOnClickListener { viewModel.clickMovie(movie) }
        val genreRecyclerView: RecyclerView = itemView.findViewById(R.id.movie_genre_list)
        genreRecyclerView.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
        genreRecyclerView.adapter = GenresAdapter(movie.genres)
    }
}