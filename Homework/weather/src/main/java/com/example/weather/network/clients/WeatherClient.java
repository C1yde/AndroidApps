package com.example.weather.network.clients;

import com.example.weather.data.models.DayForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherClient {

    @GET("/data/2.5/weather")
    Call<DayForecastResponse> getDailyForecastByCity(@Query("q") String cityName);

}