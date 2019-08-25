package com.example.themoviedb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.themoviedb.models.Movie;

import java.util.ArrayList;

public class MoviesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "moviesDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MOVIES = "movies";
    private static final String KEY_MOVIE_ID = "id";
    private static final String KEY_MOVIE_TITLE = "title";
    private static final String KEY_MOVIE_POSTER_PATH = "posterPath";
    private static final String KEY_MOVIE_RATING = "rating";
    private static final String KEY_MOVIE_IS_WATCHED = "isWatched";

    private static MoviesDatabaseHelper sInstance;

    public static synchronized MoviesDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MoviesDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private MoviesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES +
                "(" +
                KEY_MOVIE_ID + " INTEGER PRIMARY KEY," +
                KEY_MOVIE_TITLE + " TEXT," +
                KEY_MOVIE_POSTER_PATH + " TEXT," +
                KEY_MOVIE_RATING + " INTEGER," +
                KEY_MOVIE_IS_WATCHED + " INTEGER" +
                ")";

        database.execSQL(CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
            onCreate(database);
        }
    }

    public Movie getMovie(String title){
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = KEY_MOVIE_TITLE + " = ?";
        String[] selectionArgs = { title };

        Cursor cursor = db.query(
                TABLE_MOVIES,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()){
            Movie movie = new Movie();

            movie.title = cursor.getString(cursor.getColumnIndex(KEY_MOVIE_TITLE));
            movie.posterPath = cursor.getString(cursor.getColumnIndex(KEY_MOVIE_POSTER_PATH));
            movie.rating = cursor.getInt(cursor.getColumnIndex(KEY_MOVIE_RATING)) != 0;
            movie.rating = cursor.getInt(cursor.getColumnIndex(KEY_MOVIE_IS_WATCHED)) != 0;

            cursor.close();
            return movie;
        } else {
            cursor.close();
            return null;
        }
    }

    public ArrayList<Movie> getAllMovies(boolean isWatched){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Movie> list = new ArrayList<>();

        String selection = KEY_MOVIE_IS_WATCHED + " = ?";
        String[] selectionArgs = { isWatched ? "1" : "0" };

        try (Cursor cursor = db.query(
                TABLE_MOVIES,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        )) {

            if (cursor.moveToFirst()) {
                do {
                    Movie movie = new Movie();

                    movie.title = cursor.getString(cursor.getColumnIndex(KEY_MOVIE_TITLE));
                    movie.posterPath = cursor.getString(cursor.getColumnIndex(KEY_MOVIE_POSTER_PATH));
                    movie.rating = cursor.getInt(cursor.getColumnIndex(KEY_MOVIE_RATING)) != 0;
                    movie.rating = cursor.getInt(cursor.getColumnIndex(KEY_MOVIE_IS_WATCHED)) != 0;

                    list.add(movie);
                } while (cursor.moveToNext());
            }
        }

        return list;
    }

    public void addMovie(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MOVIE_TITLE, movie.title);
        values.put(KEY_MOVIE_POSTER_PATH, movie.posterPath);
        values.put(KEY_MOVIE_RATING, movie.rating);
        values.put(KEY_MOVIE_IS_WATCHED, movie.isWatched);

        db.insert(TABLE_MOVIES, null, values);
    }

    public void updateMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MOVIE_IS_WATCHED, movie.isWatched);
        values.put(KEY_MOVIE_RATING, movie.rating);

        db.update(TABLE_MOVIES, values, KEY_MOVIE_TITLE + " = ?",
                new String[]{String.valueOf(movie.title)});
    }

    public void deleteMovie(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = KEY_MOVIE_ID + " = ?";
        String[] selectionArgs = { String.valueOf(movie.id)};

        db.delete(TABLE_MOVIES, selection, selectionArgs);
    }
}
