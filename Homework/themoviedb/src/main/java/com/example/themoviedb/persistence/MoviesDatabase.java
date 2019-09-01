package com.example.themoviedb.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.themoviedb.R;

@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {

    private static volatile MoviesDatabase INSTANCE;

    public abstract MovieDao movieDao();

    public static MoviesDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesDatabase.class, context.getString(R.string.movies_db_name))
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}