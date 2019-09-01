package com.example.themoviedb.persistence;

import com.example.themoviedb.MovieDataSource;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalMovieDataSource implements MovieDataSource {

    private final MovieDao mMovieDao;

    public LocalMovieDataSource(MovieDao movieDao) {
        mMovieDao = movieDao;
    }

    @Override
    public Flowable<Movie> getMovie() {
        return mMovieDao.getMovie();
    }

    @Override
    public Completable insertOrUpdateMovie(Movie movie) {
        return mMovieDao.insertMovie(movie);
    }
}