package com.example.weather.data.repository.weather;

import android.support.annotation.NonNull;

import com.example.weather.data.models.FiveDayForecastResponse;

import retrofit2.Call;

public interface WeatherRepository {

    Call<FiveDayForecastResponse> getFiveDayForecast(@NonNull String city);

}