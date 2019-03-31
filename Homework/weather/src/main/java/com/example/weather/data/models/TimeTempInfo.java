package com.example.weather.data.models;

import com.google.gson.annotations.SerializedName;

public class TimeTempInfo {

    @SerializedName("temp")
    private float temp;

    public float getTemp() {
        return temp;
    }
}