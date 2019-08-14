package com.example.chuckorcats.repository.chuck;

import androidx.annotation.NonNull;

import com.example.chuckorcats.models.ChuckResponse;
import com.example.chuckorcats.network.clients.ChuckClient;

import retrofit2.Call;

public class ChuckRepositoryImpl implements ChuckRepository {

    @NonNull
    private final ChuckClient client;

    public ChuckRepositoryImpl(@NonNull ChuckClient client) {
        this.client = client;
    }

    @Override
    public Call<ChuckResponse> getJokesAboutChuck(@NonNull Integer count) {
        return client.getJokesAboutChuck(count);
    }
}