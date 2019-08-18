package com.example.themoviedb.models;

import com.google.gson.annotations.SerializedName;

public class MovieModel {

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("original_title")
    public String title;

    @SerializedName("vote_average")
    public double rating;

    @SerializedName("overview")
    private String overview;
}
