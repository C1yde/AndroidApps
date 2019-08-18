package com.example.themoviedb.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.themoviedb.network.ApiFactory;
import com.example.themoviedb.network.clients.MovieDBClient;

public class RepositoryProvider {

    private static volatile RepositoryProvider sInstance;

    @Nullable
    private MovieRepository repository;

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
    public MovieRepository provideRepository() {
        if (repository == null) {
            repository = new MovieRepositoryImpl(getServiceInstance(MovieDBClient.class));
        }
        return repository;
    }
}