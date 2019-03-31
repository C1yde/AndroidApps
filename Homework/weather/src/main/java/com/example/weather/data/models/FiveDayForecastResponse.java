package com.example.weather.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FiveDayForecastResponse {

    @SerializedName("city")
    private City city;

    @SerializedName("list")
    private ArrayList<WeatherInfo> weatherByTimeInfo;

    public City getCity() {
        return city;
    }
}
