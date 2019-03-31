package com.example.weather.data.models;

import com.google.gson.annotations.SerializedName;

public class WeatherDescription {

    @SerializedName("icon")
    private String iconName;

    public String getIconUrl(){
        return "http://openweathermap.org/img/w/" + iconName + ".png";
    }
}
