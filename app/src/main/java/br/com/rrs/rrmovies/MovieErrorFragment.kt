package br.com.rrs.rrmovies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class MovieErrorFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SUCESSO_error", "###### sucesso #####")

        return inflater.inflate(R.layout.fragment_movie_error, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            MovieErrorFragment()
    }

}
