package com.example.themoviedb;

import android.content.Context;

public class Utilities {

    public static String getMoviePosterLink(Context context, String posterPath){
        String imageLink = context.getString(R.string.posterLink);
        return imageLink + posterPath;
    }
}
