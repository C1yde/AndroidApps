package com.example.weather.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FiveDayForecastResponse {

    @SerializedName("list")
    private ArrayList<WeatherInfo> weatherByTimeInfo;

    public ArrayList<WeatherInfo> getWeatherByTimeInfo() { return weatherByTimeInfo; }
}
