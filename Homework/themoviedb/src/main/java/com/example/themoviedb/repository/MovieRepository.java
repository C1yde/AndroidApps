package com.example.themoviedb.repository;

import androidx.annotation.NonNull;

import com.example.themoviedb.models.MovieResponse;

import io.reactivex.Observable;

public interface MovieRepository {

    Observable<MovieResponse> getMovies(@NonNull String searchString);

}