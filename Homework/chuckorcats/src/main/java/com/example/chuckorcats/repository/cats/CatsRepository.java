package com.example.chuckorcats.repository.cats;

import androidx.annotation.NonNull;

import com.example.chuckorcats.models.Fact;

import java.util.ArrayList;

import retrofit2.Call;

public interface CatsRepository {

    Call<ArrayList<Fact>> getFactsAboutCats(@NonNull Integer amount);

}