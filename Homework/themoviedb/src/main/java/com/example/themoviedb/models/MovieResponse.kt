package com.example.themoviedb.models

import com.google.gson.annotations.SerializedName
import java.util.*

class MovieResponse {

    @SerializedName("results")
    val movies: ArrayList<MovieModel>? = null
}