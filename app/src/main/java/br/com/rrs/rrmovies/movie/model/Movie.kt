package br.com.rrs.rrmovies.movie.model

import android.os.Parcelable
import br.com.rrs.rrmovies.genre.model.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val backdrop_path: String?,
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val genres: List<Genre>
) : Parcelable
