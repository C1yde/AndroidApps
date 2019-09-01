package com.example.themoviedb.network.clients

import com.example.themoviedb.models.MovieResponse

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBClient {

    @GET("3/search/movie")
    fun getMovies(@Query("query") searchString: String): Observable<MovieResponse>

}