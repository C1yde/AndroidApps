package com.example.chuckorcats.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chuckorcats.network.ApiFactory;
import com.example.chuckorcats.network.clients.CatsClient;
import com.example.chuckorcats.network.clients.ChuckClient;
import com.example.chuckorcats.repository.cats.CatsRepository;
import com.example.chuckorcats.repository.cats.CatsRepositoryImpl;
import com.example.chuckorcats.repository.chuck.ChuckRepository;
import com.example.chuckorcats.repository.chuck.ChuckRepositoryImpl;

public class RepositoryProvider {

    private static volatile RepositoryProvider sInstance;

    @Nullable
    private CatsRepository catsRepository;

    @Nullable
    private ChuckRepository chuckRepository;

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
    private static <T> T getCatsServiceInstance(Class<T> clazz) {
        return ApiFactory.getCatsRetrofitInstance().create(clazz);
    }

    private static <T> T getChuckServiceInstance(Class<T> clazz) {
        return ApiFactory.getChuckRetrofitInstance().create(clazz);
    }

    @NonNull
    public CatsRepository provideCatsRepository() {
        if (catsRepository == null) {
            catsRepository = new CatsRepositoryImpl(getCatsServiceInstance(CatsClient.class));
        }
        return catsRepository;
    }

    @NonNull
    public ChuckRepository provideChuckRepository() {
        if (chuckRepository == null) {
            chuckRepository = new ChuckRepositoryImpl(getChuckServiceInstance(ChuckClient.class));
        }
        return chuckRepository;
    }

}