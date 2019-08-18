package com.example.themoviedb.network.clients;

import com.example.themoviedb.models.MovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDBClient {

    @GET("3/search/movie")
    Single<MovieResponse> getMovies(@Query("query") String searchString);

}