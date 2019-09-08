package com.example.themoviedb.persistence

import com.example.themoviedb.MovieDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

class LocalMovieDataSource(private val mMovieDao: MovieDao) : MovieDataSource {

    override val allMovies: Flowable<List<Movie>>
        get() = mMovieDao.allMovies

    override val watchedMovies: Flowable<List<Movie>>
        get() = mMovieDao.watchedMovies

    override fun getMovie(title: String?): Maybe<Movie> {
        return mMovieDao.getMovie(title)
    }

    override fun insertMovie(movie: Movie): Completable {
        return mMovieDao.insertMovie(movie)
    }

    override fun updateMovie(movie: Movie): Completable {
        return mMovieDao.updateMovie(movie)
    }

    override fun deleteMovie(movie: Movie): Int {
        return mMovieDao.deleteMovie(movie)
    }
}