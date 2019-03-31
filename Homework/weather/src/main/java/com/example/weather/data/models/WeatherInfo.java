package com.example.weather.data.models;

import android.text.format.DateFormat;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class WeatherInfo {

    @SerializedName("dt")
    private long dateTime;

    @SerializedName("main")
    private TimeTempInfo timeTempInfo;

    @SerializedName("weather")
    private ArrayList<WeatherDescription> weatherList;

    public void setWeatherImage(ImageView imageView){
        if (weatherList != null) {
            Picasso.get().load(this.weatherList.get(0).getIconUrl()).into(imageView);
        }
    }

    public String getDateText(){
        long millisecond = this.dateTime * 1000;
        Date weatherDate =  new Date(millisecond);
        return DateFormat.format("dd.MM.yyyy", weatherDate).toString();
    }

    public String getTemperatureText(){
        return null;
    }
}