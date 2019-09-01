package com.example.themoviedb.network

import com.example.themoviedb.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpProvider {

    fun provideClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(MovieDBApiKeyInterceptor())
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            builder.addNetworkInterceptor(StethoInterceptor())
        }

        return builder.build()
    }

}