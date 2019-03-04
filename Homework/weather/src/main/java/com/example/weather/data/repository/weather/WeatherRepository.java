package com.example.weather.data.repository.weather;

import android.support.annotation.NonNull;

import com.example.weather.data.models.DayForecastResponse;

import retrofit2.Call;

public interface WeatherRepository {

    Call<DayForecastResponse> getDayForecast(@NonNull String city);

}