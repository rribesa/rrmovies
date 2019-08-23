package br.com.rrs.rrmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val host = NavHostFragment.create(R.navigation.navigation_movie)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_main, host).setPrimaryNavigationFragment(host)
            .commit()
    }

}
