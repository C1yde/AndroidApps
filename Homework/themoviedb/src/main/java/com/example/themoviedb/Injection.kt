package com.example.themoviedb

import android.content.Context

import com.example.themoviedb.persistence.LocalMovieDataSource
import com.example.themoviedb.persistence.MoviesDatabase

object Injection {

    fun provideMovieDataSource(context: Context?): MovieDataSource {
        val database = MoviesDatabase.getInstance(context)
        return LocalMovieDataSource(database!!.movieDao())
    }
}