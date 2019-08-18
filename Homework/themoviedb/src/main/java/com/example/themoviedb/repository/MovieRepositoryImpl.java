package com.example.themoviedb.repository;

import androidx.annotation.NonNull;

import com.example.themoviedb.models.MovieResponse;
import com.example.themoviedb.network.clients.MovieDBClient;

import retrofit2.Call;

public class MovieRepositoryImpl implements MovieRepository {

    @NonNull
    private final MovieDBClient client;

    public MovieRepositoryImpl(@NonNull MovieDBClient client) {
        this.client = client;
    }

    @Override
    public Call<MovieResponse> getMovies(@NonNull String searchString) {
        return client.getMovies(searchString);
    }
}