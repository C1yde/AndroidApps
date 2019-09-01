package com.example.themoviedb.network

import com.example.themoviedb.BuildConfig

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private var sRetrofit: Retrofit? = null

    private var sHttpClient: OkHttpClient? = null

    val retrofitInstance: Retrofit?
        get() {
            if (sRetrofit == null) {
                sRetrofit = Retrofit.Builder()
                        .baseUrl(BuildConfig.THE_MOVIE_DB_URL)
                        .client(provideClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return sRetrofit
        }

    private fun provideClient(): OkHttpClient {
        if (sHttpClient == null) {
            sHttpClient = OkHttpProvider.provideClient()
        }
        return sHttpClient as OkHttpClient
    }
}