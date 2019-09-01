package com.example.themoviedb.repository

import com.example.themoviedb.models.MovieResponse
import com.example.themoviedb.network.clients.MovieDBClient

import io.reactivex.Observable

class MovieRepositoryImpl(private val client: MovieDBClient) : MovieRepository {

    override fun getMovies(searchString: String): Observable<MovieResponse> {
        return client.getMovies(searchString)
    }
}