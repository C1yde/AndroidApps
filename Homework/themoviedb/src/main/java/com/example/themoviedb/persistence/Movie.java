package com.example.themoviedb.persistence;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "movies")
public class Movie {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "movieid")
    private String mId;

    @ColumnInfo(name = "posterPath")
    private String mPosterPath;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "rating")
    private boolean mRating;

    @ColumnInfo(name = "iswatched")
    private boolean mIsWatched;

    @Ignore
    public Movie(String title) {
        this.mId = UUID.randomUUID().toString();
        this.mTitle = title;
        this.mIsWatched = false;
        this.mRating = false;
    }

    public Movie(String id, String title, String posterPath, Boolean isWatched, Boolean rating) {
        this.mId = id;
        this.mTitle = title;
        this.mPosterPath = posterPath;
        this.mIsWatched = isWatched;
        this.mRating = rating;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean getRating() {
        return mRating;
    }

    public boolean getIsWatched() {
        return mIsWatched;
    }

    public String getId() {
        return mId;
    }
}
