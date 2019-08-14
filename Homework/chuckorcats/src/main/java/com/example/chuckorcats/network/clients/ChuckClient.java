package com.example.chuckorcats.network.clients;

import com.example.chuckorcats.models.ChuckResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChuckClient {

    @GET("/jokes/random/{count}")
    Call<ChuckResponse> getJokesAboutChuck(@Path("count") Integer count);

}