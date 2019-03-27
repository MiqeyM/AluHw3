package pl.daftacademy.androidlevelup.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pl.daftacademy.androidlevelup.data.MovieDao
import pl.daftacademy.androidlevelup.data.MovieFileDao
import pl.daftacademy.androidlevelup.entity.Movie

class MoviesViewModel(application: Application): AndroidViewModel(application) {
    private val movieDao: MovieDao = MovieFileDao(getApplication())

    fun getMovies() = movieDao.getAllMovies()

    fun getFilteredMovies(category: String = "All"): List<Movie>{
        val filteredMovieList = movieDao.getAllMovies().toMutableList()
        if(!category.equals("All"))
        filteredMovieList.retainAll{ it.genres.contains(category) }

        return filteredMovieList
    }
}
