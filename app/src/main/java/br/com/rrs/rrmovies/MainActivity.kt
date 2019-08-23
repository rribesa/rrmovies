package br.com.rrs.rrmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.rrs.rrmovies.genre.ui.fragment.GenreListFragment
import br.com.rrs.rrmovies.movie.fragment.MovieListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goMovie()
    }

    private fun goGenre() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_layout, GenreListFragment.newInstance())
        ft.addToBackStack(null)
        ft.commit()
    }

    private fun goMovie() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_layout, MovieListFragment.newInstance())
        ft.addToBackStack(null)
        ft.commit()
    }
}
