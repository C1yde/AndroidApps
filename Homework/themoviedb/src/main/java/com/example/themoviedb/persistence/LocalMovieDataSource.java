package com.example.themoviedb.persistence;

import com.example.themoviedb.MovieDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalMovieDataSource implements MovieDataSource {

    private final MovieDao mMovieDao;

    public LocalMovieDataSource(MovieDao movieDao) {
        mMovieDao = movieDao;
    }

    @Override
    public Flowable<Movie> getMovie(String title) {
        return mMovieDao.getMovie(title);
    }

    @Override
    public Flowable<List<Movie>> getAllMovies(){
        return mMovieDao.getAllMovies();
    }

    @Override
    public Flowable<List<Movie>> getWatchedMovies(){
        return mMovieDao.getWatchedMovies();
    }

    @Override
    public Completable insertOrUpdateMovie(Movie movie) {
        return mMovieDao.insertMovie(movie);
    }

    @Override
    public Completable deleteMovie(Movie movie) {
        return mMovieDao.deleteMovie(movie);
    }
}