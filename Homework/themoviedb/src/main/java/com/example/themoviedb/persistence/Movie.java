package com.example.themoviedb.persistence;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.themoviedb.models.MovieModel;

import java.util.UUID;

@Entity(tableName = "movies")
public class Movie {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "movieid")
    private String id;

    @ColumnInfo(name = "posterPath")
    private String posterPath;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "rating")
    private boolean rating;

    @ColumnInfo(name = "iswatched")
    private boolean isWatched;

    @Ignore
    public Movie(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.isWatched = false;
        this.rating = false;
    }

    public Movie(String id, String title, String posterPath) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
    }

    public Movie(MovieModel movieModel) {
        this.id = UUID.randomUUID().toString();
        this.title = movieModel.title;
        this.posterPath = movieModel.posterPath;
        this.isWatched = false;
        this.rating = false;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public boolean getRating() {
        return rating;
    }

    public void setRating(Boolean rating) {
        this.rating = rating;
    }

    public boolean getIsWatched() {
        return isWatched;
    }

    public void setIsWatched(Boolean isWatched) {
        this.isWatched = isWatched;
    }

    public String getId() {
        return id;
    }
}
