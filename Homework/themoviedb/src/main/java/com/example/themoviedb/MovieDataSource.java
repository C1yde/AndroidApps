package com.example.themoviedb;

import com.example.themoviedb.persistence.Movie;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface MovieDataSource {

    Flowable<Movie> getMovie();

    Completable insertOrUpdateMovie(Movie movie);
}