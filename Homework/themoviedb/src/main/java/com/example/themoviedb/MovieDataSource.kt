package com.example.themoviedb

import com.example.themoviedb.persistence.Movie

import io.reactivex.Completable
import io.reactivex.Flowable

interface MovieDataSource {

    val allMovies: Flowable<List<Movie>>

    val watchedMovies: Flowable<List<Movie>>

    fun getMovie(title: String?): Flowable<Movie>

    fun insertOrUpdateMovie(movie: Movie): Completable

    fun deleteMovie(movie: Movie): Completable
}