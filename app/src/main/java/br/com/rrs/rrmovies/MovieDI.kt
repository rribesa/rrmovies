package br.com.rrs.rrmovies

import br.com.rrs.rrmovies.movie.MovieUseCase
import br.com.rrs.rrmovies.movie.repository.MovieRepository
import br.com.rrs.rrmovies.movie.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object MovieDI {
    val viewModelModule = module {
        viewModel { MovieViewModel(useCase = get()) }
        factory { MovieUseCase(repository = get()) }
        factory { MovieRepository(service = get()) }
        single { WebClient().service() }
    }

    fun init() = loadKoinModules(viewModelModule)
}

