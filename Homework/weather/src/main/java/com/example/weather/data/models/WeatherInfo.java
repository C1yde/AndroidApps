package com.example.weather.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherInfo {

    @SerializedName("dt")
    private float dateTime;

    @SerializedName("main")
    private TimeTempInfo timeTempInfo;

    @SerializedName("weather")
    private ArrayList<WeatherDescription> weatherList;
}