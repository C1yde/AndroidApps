package com.example.themoviedb.network;

import androidx.annotation.NonNull;

import com.example.themoviedb.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiFactory {

    private static Retrofit sRetrofit;

    private static OkHttpClient sHttpClient;

    private ApiFactory() {
    }

    @NonNull
    public static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.THE_MOVIE_DB_URL)
                    .client(provideClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    @NonNull
    private static OkHttpClient provideClient() {
        if (sHttpClient == null) {
            sHttpClient = OkHttpProvider.provideClient();
        }
        return sHttpClient;
    }
}