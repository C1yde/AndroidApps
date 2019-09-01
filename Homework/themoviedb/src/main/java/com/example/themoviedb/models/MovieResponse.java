package com.example.themoviedb.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {

    @SerializedName("results")
    private ArrayList<MovieModel> movies;

    public ArrayList<MovieModel> getMovies() { return movies; }
}