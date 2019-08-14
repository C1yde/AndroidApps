package com.example.chuckorcats.repository.cats;

import androidx.annotation.NonNull;

import com.example.chuckorcats.models.Fact;
import com.example.chuckorcats.network.clients.CatsClient;

import java.util.ArrayList;

import retrofit2.Call;

public class CatsRepositoryImpl implements CatsRepository {

    private static final String ANIMAL_TYPE = "cat";

    @NonNull
    private final CatsClient client;

    public CatsRepositoryImpl(@NonNull CatsClient client) {
        this.client = client;
    }

    @Override
    public Call<ArrayList<Fact>> getFactsAboutCats(@NonNull Integer amount) {
        return client.getFactsAboutCats(ANIMAL_TYPE, amount);
    }
}