package com.example.themoviedb.persistence

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface MovieDao {

    @get:Query("SELECT * FROM movies")
    val allMovies: Flowable<List<Movie>>

    @get:Query("SELECT * FROM movies WHERE iswatched == 1")
    val watchedMovies: Flowable<List<Movie>>

    @Query("SELECT * FROM movies WHERE title == (:title)")
    fun getMovie(title: String?): Maybe<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movie: Movie): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun updateMovie(movie: Movie): Completable

    @Delete
    fun deleteMovie(movie: Movie): Int
}
