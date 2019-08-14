package com.example.chuckorcats.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChuckResponse {

    @SerializedName("type")
    private boolean type;

    @SerializedName("value")
    private ArrayList<Joke> jokes;

    public ArrayList<Joke> getJokes() { return jokes; }
}