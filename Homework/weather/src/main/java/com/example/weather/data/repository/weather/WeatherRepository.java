package com.example.weather.data.repository.weather;

import com.example.weather.data.models.FiveDayForecastResponse;

import androidx.annotation.NonNull;
import retrofit2.Call;

public interface WeatherRepository {

    Call<FiveDayForecastResponse> getFiveDayForecast(@NonNull String city);

}