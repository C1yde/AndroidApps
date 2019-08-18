package com.example.themoviedb.network.clients;

import com.example.themoviedb.models.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDBClient {

    @GET("3/search/movie")
    Observable<MovieResponse> getMovies(@Query("query") String searchString);

}