package com.example.themoviedb.persistence

import com.example.themoviedb.MovieDataSource

import io.reactivex.Completable
import io.reactivex.Flowable

class LocalMovieDataSource(private val mMovieDao: MovieDao) : MovieDataSource {

    override val allMovies: Flowable<List<Movie>>
        get() = mMovieDao.allMovies

    override val watchedMovies: Flowable<List<Movie>>
        get() = mMovieDao.watchedMovies

    override fun getMovie(title: String?): Flowable<Movie> {
        return mMovieDao.getMovie(title)
    }

    override fun insertOrUpdateMovie(movie: Movie): Completable {
        return mMovieDao.insertMovie(movie)
    }

    override fun deleteMovie(movie: Movie): Completable {
        return mMovieDao.deleteMovie(movie)
    }
}