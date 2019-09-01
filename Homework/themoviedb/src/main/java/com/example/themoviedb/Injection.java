package com.example.themoviedb;

import android.content.Context;

import com.example.themoviedb.persistence.LocalMovieDataSource;
import com.example.themoviedb.persistence.MoviesDatabase;

public class Injection {

    public static MovieDataSource provideUserDataSource(Context context) {
        MoviesDatabase database = MoviesDatabase.getInstance(context);
        return new LocalMovieDataSource(database.movieDao());
    }
}