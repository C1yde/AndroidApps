package com.example.themoviedb

import android.content.Context

object Utilities {

    fun getMoviePosterLink(context: Context, posterPath: String?): String {
        val imageLink = context.getString(R.string.posterLink)
        return imageLink + posterPath
    }
}
