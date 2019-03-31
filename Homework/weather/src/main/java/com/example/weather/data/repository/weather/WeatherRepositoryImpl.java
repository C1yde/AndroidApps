package com.example.weather.data.repository.weather;

import android.support.annotation.NonNull;

import com.example.weather.data.models.FiveDayForecastResponse;
import com.example.weather.network.clients.WeatherClient;

import retrofit2.Call;

public class WeatherRepositoryImpl implements WeatherRepository {

    @NonNull
    private final WeatherClient client;

    public WeatherRepositoryImpl(@NonNull WeatherClient client) {
        this.client = client;
    }

    @Override
    public Call<FiveDayForecastResponse> getFiveDayForecast(@NonNull String city) {
        return client.getFiveDayForecastByCity(city);
    }
}