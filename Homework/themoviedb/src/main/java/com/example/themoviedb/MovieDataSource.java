package com.example.themoviedb;

import com.example.themoviedb.persistence.Movie;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface MovieDataSource {

    Flowable<Movie> getMovie(String title);

    Flowable<List<Movie>> getAllMovies();

    Flowable<List<Movie>> getWatchedMovies();

    Completable insertOrUpdateMovie(Movie movie);

    Completable deleteMovie(Movie movie);
}