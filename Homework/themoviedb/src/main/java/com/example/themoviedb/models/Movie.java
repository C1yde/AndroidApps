package com.example.themoviedb.models;

import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

public class Movie {

    private final String imageLink = "https://image.tmdb.org/t/p/w185/";

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("original_title")
    public String title;

    public Integer id;

    public boolean rating = false;

    public boolean isWatched = false;

    public void setPoster(ImageView imageView){ Picasso.get().load(this.imageLink + posterPath).into(imageView); }
}
