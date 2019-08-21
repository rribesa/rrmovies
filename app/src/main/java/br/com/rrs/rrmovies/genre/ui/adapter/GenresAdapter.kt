package br.com.rrs.rrmovies.genre.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rrs.rrmovies.R
import br.com.rrs.rrmovies.genre.model.Genre
import br.com.rrs.rrmovies.genre.model.Genres

class GenresAdapter(
    private val genres: Genres
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_list_item, parent, false)
        return GenresViewHolder(view)
    }

    override fun getItemCount() = genres.genres.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GenresViewHolder).bindGenre(genres.genres[position])
    }
}

class GenresViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bindGenre(genre: Genre) {
        val idView: TextView = itemView.findViewById(R.id.category_list_genre_id)
        idView.text = genre.id.toString()
        val genreNameView: TextView = itemView.findViewById(R.id.category_list_genre_name)
        genreNameView.text = genre.name
    }
}