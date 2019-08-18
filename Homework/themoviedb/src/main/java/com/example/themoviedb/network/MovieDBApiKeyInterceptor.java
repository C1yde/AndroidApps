package com.example.themoviedb.network;

import androidx.annotation.NonNull;

import com.example.themoviedb.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MovieDBApiKeyInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request original = chain.request();
        final HttpUrl originalHttpUrl = original.url();
        final HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API_KEY)
                .build();

        return chain.proceed(original.newBuilder().url(url).build());
    }
}