package com.example.themoviedb.network

import com.example.themoviedb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class MovieDBApiKeyInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API_KEY)
                .build()

        return chain.proceed(original.newBuilder().url(url).build())
    }
}