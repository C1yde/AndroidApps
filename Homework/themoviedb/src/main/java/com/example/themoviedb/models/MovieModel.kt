package com.example.themoviedb.models

import com.google.gson.annotations.SerializedName

class MovieModel {

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("original_title")
    var title: String? = null
}
