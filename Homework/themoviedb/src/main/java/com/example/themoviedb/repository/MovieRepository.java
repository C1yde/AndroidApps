package com.example.themoviedb.repository;

import androidx.annotation.NonNull;

import com.example.themoviedb.models.MovieResponse;

import retrofit2.Call;

public interface MovieRepository {

    Call<MovieResponse> getMovies(@NonNull String searchString);

}