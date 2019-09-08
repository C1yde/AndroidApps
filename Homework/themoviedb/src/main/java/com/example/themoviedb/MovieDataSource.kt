package com.example.themoviedb

import com.example.themoviedb.persistence.Movie
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

interface MovieDataSource {

    val allMovies: Flowable<List<Movie>>

    val watchedMovies: Flowable<List<Movie>>

    fun getMovie(title: String?): Maybe<Movie>

    fun insertMovie(movie: Movie): Completable

    fun updateMovie(movie: Movie): Completable

    fun deleteMovie(movie: Movie): Int
}