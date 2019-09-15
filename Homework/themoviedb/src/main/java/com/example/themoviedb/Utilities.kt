package com.example.themoviedb

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

object Utilities {

    fun setImageResource(context: Context, imageView: ImageView, imagePath: String?){
        Picasso.get()
                .load(getMoviePosterLink(context, imagePath))
                .into(imageView)
    }

    private fun getMoviePosterLink(context: Context, posterPath: String?): String {
        val imageLink = context.getString(R.string.posterLink)
        return imageLink + posterPath
    }
}
