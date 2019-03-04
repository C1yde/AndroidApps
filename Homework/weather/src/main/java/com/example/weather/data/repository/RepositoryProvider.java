package com.example.weather.data.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.weather.data.repository.weather.WeatherRepository;
import com.example.weather.data.repository.weather.WeatherRepositoryImpl;
import com.example.weather.network.ApiFactory;
import com.example.weather.network.clients.WeatherClient;

public class RepositoryProvider {

    private static volatile RepositoryProvider sInstance;

    @Nullable
    private WeatherRepository weatherRepository;

    private RepositoryProvider() {
    }

    public static RepositoryProvider get() {
        if (sInstance == null) {
            synchronized (RepositoryProvider.class) {
                if (sInstance == null) {
                    sInstance = new RepositoryProvider();
                }
            }
        }
        return sInstance;
    }

    @NonNull
    private static <T> T getServiceInstance(Class<T> clazz) {
        return ApiFactory.getRetrofitInstance().create(clazz);
    }

    @NonNull
    public WeatherRepository provideNewsFeedRepository() {
        if (weatherRepository == null) {
            weatherRepository = new WeatherRepositoryImpl(getServiceInstance(WeatherClient.class));
        }
        return weatherRepository;
    }
}