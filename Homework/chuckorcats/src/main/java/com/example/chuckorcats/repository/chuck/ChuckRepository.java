package com.example.chuckorcats.repository.chuck;

import androidx.annotation.NonNull;

import com.example.chuckorcats.models.ChuckResponse;

import retrofit2.Call;

public interface ChuckRepository {

    Call<ChuckResponse> getJokesAboutChuck(@NonNull Integer count);

}