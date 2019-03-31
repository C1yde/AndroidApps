package com.example.weather.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.data.models.WeatherInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherInfoAdapter extends RecyclerView.Adapter<WeatherInfoAdapter.WeatherInfoViewHolder> {
    private ArrayList<WeatherInfo> mWeatherInfo;

    public WeatherInfoAdapter(){
        mWeatherInfo = new ArrayList<>();
    }

    public void setWeatherInfo(ArrayList<WeatherInfo> weatherInfo){
        mWeatherInfo.clear();
        mWeatherInfo.addAll(weatherInfo);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weathercard, viewGroup, false);
        return new WeatherInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherInfoViewHolder postViewHolder, int i) {
        final WeatherInfo currentItem = mWeatherInfo.get(i);

        currentItem.setWeatherImage(postViewHolder.weatherImage);
        postViewHolder.temperatureText.setText(currentItem.getTemperatureText());
        postViewHolder.dateText.setText(currentItem.getDateText());
    }

    @Override
    public int getItemCount() {
        return mWeatherInfo.size();
    }

    static class WeatherInfoViewHolder extends RecyclerView.ViewHolder {
        TextView temperatureText;
        TextView dateText;
        ImageView weatherImage;

        WeatherInfoViewHolder(View itemView) {
            super(itemView);
            temperatureText = itemView.findViewById(R.id.temperatureText);
            dateText = itemView.findViewById(R.id.dateText);
            weatherImage = itemView.findViewById(R.id.weatherImage);
        }
    }
}
