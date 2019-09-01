package com.example.themoviedb.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies LIMIT 1")
    Flowable<Movie> getMovie();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMovie(Movie movie);

}
