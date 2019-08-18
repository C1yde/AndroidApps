package com.example.themoviedb.repository;

import androidx.annotation.NonNull;

import com.example.themoviedb.models.MovieResponse;

import io.reactivex.Single;

public interface MovieRepository {

    Single<MovieResponse> getMovies(@NonNull String searchString);

}