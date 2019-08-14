package com.example.chuckorcats.network.clients;

import com.example.chuckorcats.models.Fact;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatsClient {

    @GET("/facts/random")
    Call<ArrayList<Fact>> getFactsAboutCats(@Query("animal_type") String animalType, @Query("amount") Integer amount);

}