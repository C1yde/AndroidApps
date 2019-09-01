package com.example.themoviedb.repository

import com.example.themoviedb.models.MovieResponse

import io.reactivex.Observable

interface MovieRepository {

    fun getMovies(searchString: String): Observable<MovieResponse>

}