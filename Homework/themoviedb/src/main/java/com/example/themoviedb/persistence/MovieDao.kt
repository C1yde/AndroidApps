package com.example.themoviedb.persistence

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @get:Query("SELECT * FROM movies")
    val allMovies: Flowable<List<Movie>>

    @get:Query("SELECT * FROM movies WHERE iswatched == 1")
    val watchedMovies: Flowable<List<Movie>>

    @Query("SELECT * FROM movies WHERE title == (:title)")
    fun getMovie(title: String?): Flowable<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Long

    @Delete
    fun deleteMovie(movie: Movie): Int
}
