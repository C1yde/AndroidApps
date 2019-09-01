package com.example.themoviedb.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies WHERE title == (:title)")
    Flowable<Movie> getMovie(String title);

    @Query("SELECT * FROM movies")
    Flowable<List<Movie>> getAllMovies();

    @Query("SELECT * FROM movies WHERE iswatched == 1")
    Flowable<List<Movie>> getWatchedMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMovie(Movie movie);

    @Delete()
    Completable deleteMovie(Movie movie);
}
