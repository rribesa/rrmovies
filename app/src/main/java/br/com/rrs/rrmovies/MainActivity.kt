package br.com.rrs.rrmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.rrs.rrmovies.genre.ui.fragment.GenreListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goGenre()
    }

    private fun goGenre() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_layout, GenreListFragment.newInstance())
        ft.addToBackStack(null)
        ft.commit()
    }
}
