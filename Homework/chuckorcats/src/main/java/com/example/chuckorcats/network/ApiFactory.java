package com.example.chuckorcats.network;

import androidx.annotation.NonNull;

import com.example.chuckorcats.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiFactory {

    private static Retrofit sCatsRetrofit;

    private static Retrofit sChuckRetrofit;

    private static OkHttpClient sHttpClient;

    private ApiFactory() {
    }

    @NonNull
    public static Retrofit getCatsRetrofitInstance() {
        if (sCatsRetrofit == null) {
            sCatsRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.CATS_API_URL)
                    .client(provideClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sCatsRetrofit;
    }

    public static Retrofit getChuckRetrofitInstance() {
        if (sChuckRetrofit == null) {
            sChuckRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.CHUCK_API_URL)
                    .client(provideClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sChuckRetrofit;
    }

    @NonNull
    private static OkHttpClient provideClient() {
        if (sHttpClient == null) {
            sHttpClient = OkHttpProvider.provideClient();
        }
        return sHttpClient;
    }
}